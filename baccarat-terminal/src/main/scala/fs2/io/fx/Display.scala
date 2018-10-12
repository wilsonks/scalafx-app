/*
 * Copyright (c) 2018 by Tykhe Gaming Private Limited
 *
 * Licensed under the Software License Agreement (the "License") of Tykhe Gaming Private Limited.
 * You may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://tykhegaming.github.io/LICENSE.txt.
 *
 * NOTICE
 * ALL INFORMATION CONTAINED HEREIN IS, AND REMAINS THE PROPERTY OF TYKHE GAMING PRIVATE LIMITED.
 * THE INTELLECTUAL AND TECHNICAL CONCEPTS CONTAINED HEREIN ARE PROPRIETARY TO TYKHE GAMING PRIVATE LIMITED AND
 * ARE PROTECTED BY TRADE SECRET OR COPYRIGHT LAW. DISSEMINATION OF THIS INFORMATION OR REPRODUCTION OF THIS
 * MATERIAL IS STRICTLY FORBIDDEN UNLESS PRIOR WRITTEN PERMISSION IS OBTAINED FROM TYKHE GAMING PRIVATE LIMITED.
 */

package fs2

package io

package fx

import java.util.ResourceBundle

import cats.effect.IO
import cats.implicits._
import javafx.application.Platform
import javafx.fxml.JavaFXBuilderFactory
import javafx.scene.control.Alert.AlertType
import javafx.scene.control.{Alert, ButtonType}
import javafx.scene.{Cursor, Group, Scene}
import javafx.stage.{Modality, Screen, Stage, StageStyle}
import javafx.util.Callback
import scalafx.application.JFXApp
import scalafxml.core._

import scala.reflect.runtime.universe

sealed trait Display {

  def exit(): Unit

  def load(window: Display.Window): Scene

  def open(window: Display.Window): Stage

  def restart(): Unit

  def root: Stage
}

object Display {

  def launch(io: IO[Window])(args: Array[String]): IO[Unit] =
    IO.async[Unit] { done =>
      new Launcher(io, done).main(args)
    }

  def resolveByName(kv: (String, Any)*): ExplicitDependencies = new ExplicitDependencies(kv.toMap)

  def resolveByType[A: universe.TypeTag](a: A): DependenciesByType =
    new DependenciesByType(Map(implicitly[universe.TypeTag[A]].tpe -> a))

  def resolveBySubType[A: universe.TypeTag](a: A): ControllerDependencyResolver =
    (paramName: String, dependencyType: universe.Type) =>
      if (implicitly[universe.TypeTag[A]].tpe <:< dependencyType) Some(a)
      else None

  def resolve(resolver: ControllerDependencyResolver*): ControllerDependencyResolver =
    new ControllerDependencyResolver {

      def get(paramName: String, dependencyType: universe.Type): Option[Any] =
        loop(resolver.toList, paramName, dependencyType)

      private def loop(
        remaining: List[ControllerDependencyResolver],
        paramName: String,
        dependencyType: universe.Type): Option[Any] =
        remaining match {
          case Nil          => None
          case head :: tail => head.get(paramName, dependencyType).orElse(loop(tail, paramName, dependencyType))
        }
    }

  private abstract class Manager extends Display {
    self =>

    def open(window: Window): Stage = {
      val stage = new Stage(window.style)
      stage.initModality(window.modality)
      stage.setScene(load(window))
      window.init(stage)
      stage
    }

    def load(window: Window): Scene = {
      val proxy: Callback[Class[_], Object] = (param: Class[_]) =>
        FxmlProxyGenerator(param, resolve(resolveBySubType(self), window.resolver))
      val loader = new javafx.fxml.FXMLLoader(
        getClass.getClassLoader.getResource(window.fxml),
        window.resources.orNull,
        new JavaFXBuilderFactory(),
        proxy
      ) {

        //noinspection JavaAccessorMethodOverriddenAsEmptyParen
        override def getController[T](): T = super.getController[ControllerAccessor]().as[T]
      }
      loader.load()
      new Scene(loader.getRoot[javafx.scene.Parent])
    }
  }

  case class Window(
    fxml: String,
    resolver: ControllerDependencyResolver = NoDependencyResolver,
    resources: Option[ResourceBundle] = None,
    modality: Modality = Modality.NONE,
    style: StageStyle = StageStyle.UNDECORATED,
    title: String = "Baccarat",
    cursor: Cursor = Cursor.DEFAULT,
    position: Position = Position(),
    bounds: Bounds = Bounds(),
    resizable: Boolean = true,
    maximized: Boolean = false,
    iconified: Boolean = true,
    fullscreen: Boolean = false,
    alwaysOnTop: Boolean = false,
    autoShow: Boolean = true) {

    def init(stage: Stage): Unit = {
      stage.setTitle(title)
      position.x.foreach(stage.setX)
      position.y.foreach(stage.setY)
      bounds.width.exact.foreach(stage.setWidth)
      bounds.width.min.foreach(stage.setMinWidth)
      bounds.width.max.foreach(stage.setMaxWidth)
      bounds.height.exact.foreach(stage.setHeight)
      bounds.height.min.foreach(stage.setMinHeight)
      bounds.height.max.foreach(stage.setMaxHeight)
      stage.setResizable(resizable)
      stage.setMaximized(maximized)
      stage.setFullScreen(fullscreen)
      stage.setAlwaysOnTop(alwaysOnTop)
      if (autoShow) stage.show()
    }
  }

  case class Position(x: Option[Double] = None, y: Option[Double] = None)

  case class Bounds(width: Dimension = Dimension(), height: Dimension = Dimension())

  case class Dimension(exact: Option[Double] = None, min: Option[Double] = None, max: Option[Double] = None)

  // JFXApp needs to be subclassed
  private class Launcher(io: IO[Window], done: Either[Throwable, Unit] => Unit) extends JFXApp {

    private def boot(primary: Stage): IO[Unit] =
      io.map { window =>
        // stage style cannot be set after it has been made visible
        // check to avoid failure on restart
        if (primary.getStyle != window.style) primary.initStyle(window.style)
        val display: Display = new Manager {

          def exit(): Unit = Platform.exit()

          def restart(): Unit = boot(primary)

          def root: Stage = primary
        }
        val scene = display.load(window)
        scene.setCursor(window.cursor)
        window.init(primary)
        primary.setScene(scene)
      }.recoverWith {
        case ex =>
          val bounds = Screen.getPrimary.getVisualBounds
          // alert requires a non empty stage
          primary.setScene(new Scene(new Group(), bounds.getWidth, bounds.getHeight))
          val alert =
            new Alert(AlertType.ERROR, "Do you want to restart the application?", ButtonType.YES, ButtonType.NO)
          alert.initOwner(primary)
          alert.setResizable(true)
          alert.setTitle("application start failed")
          alert.setHeaderText(ex.toString)
          alert.showAndWait().orElse(ButtonType.NO) match {
            case ButtonType.YES => boot(primary)
            case _              => IO(Platform.exit()).flatMap(_ => IO.raiseError(ex))
          }
      }

    stage = new JFXApp.PrimaryStage {
      done(boot(delegate).attempt.unsafeRunSync())
    }
  }

}
