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

import java.util.function.Consumer

import cats.effect.IO
import fs2.io.fx.Port.{Ack, Eot, Nak}
import fs2.io.fx.syntax._
import javafx.application.Platform
import javafx.concurrent.{Service, Task}
import org.reactfx.{EventSink, EventSource, EventStream}

import scala.concurrent.{ExecutionContext, ExecutionContextExecutor}

final class Port[I, O](writer: I => IO[Unit], reader: Stream[IO, O])(implicit EC: ExecutionContext)
    extends Service[Unit] with EventSink[I] with EventStream[O] {

  private val _out = new EventSource[O]
  private val _ack = new EventSource[Ack]

  def ack: EventStream[Ack] = _ack

  setExecutor(EC match {
    case ece: ExecutionContextExecutor => ece
    case _                             => EC.execute
  })

  def addObserver(observer: Consumer[_ >: O]): Unit = _out.addObserver(observer)

  def removeObserver(observer: Consumer[_ >: O]): Unit = _out.removeObserver(observer)

  def push(value: I): Unit = writer(value).unsafeRunAsync {
    case Right(_) => pushA(Ack)
    case Left(ex) => pushA(Nak(ex))
  }

  def createTask(): Task[Unit] =
    async
      .signalOf[IO, Boolean](false)
      .flatMap(halt =>
        IO.cancelable[Unit] { cb =>
          // run stream asynchronously or Task.call() will block until termination
          // stream termination will be signaled with ack
          async
            .fork(
              reader
                .to(Sink(pushO))
                .interruptWhen(halt)
                .compile
                .drain
                .runAsync {
                  case Right(_) => pushA(Eot)
                  case Left(ex) => pushA(Eot(ex))
                })
            .unsafeRunAsync(cb)
          halt.set(true)
      })
      .toTask

  private def pushA(s: Ack): IO[Unit] =
    IO.async[Unit](cb =>
      Platform.runLater(() =>
        try {
          _ack.push(s)
          cb(Port.rightUnit)
        } catch {
          case t: Throwable => cb(Left(t))
      }))

  private def pushO(t: O): IO[Unit] =
    IO.async[Unit](cb =>
      Platform.runLater(() =>
        try {
          _out.push(t)
          cb(Port.rightUnit)
        } catch {
          case t: Throwable => cb(Left(t))
      }))
}

object Port {

  def apply[O](reader: Stream[IO, O])(implicit EC: ExecutionContext): Port[Unit, O] =
    new Port(_ => IO.unit, reader)

  def apply[I, O](writer: I => IO[Unit], reader: Stream[IO, O])(implicit EC: ExecutionContext): Port[I, O] =
    new Port(writer, reader)

  private val rightUnit: Either[Throwable, Unit] = Right(())

  sealed trait Ack

  sealed trait AckRead extends Ack

  sealed trait AckWrite extends Ack

  case class Eot(cause: Throwable) extends AckRead

  case class Nak(cause: Throwable) extends AckWrite

  case object Eot extends AckRead

  case object Ack extends AckWrite

}
