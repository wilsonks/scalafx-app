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

package fs2.io.fx

import cats.effect.IO
import javafx.beans.value.{ChangeListener, ObservableValue}
import javafx.concurrent.Task
import javafx.event.{Event, EventType}
import javafx.scene.Node
import javafx.scene.input.MouseEvent
import org.reactfx.{EventStream, EventStreamBase, EventStreams, Subscription}

package object syntax {

  implicit class Io2FxOps[A](val self: IO[A]) extends AnyVal {

    def toTask: Task[A] = self.start.attempt.unsafeRunSync() match {
      case Right(f) =>
        new Task[A]() {

          override def cancelled(): Unit = f.cancel.unsafeRunSync()

          def call(): A = f.join.unsafeRunSync()
        }
      case Left(ex) =>
        () =>
          throw ex
    }
  }

  implicit class NodeReactFxOps(val self: Node) extends AnyVal {

    def events: EventStream[Event] = EventStreams.eventsOf(self, EventType.ROOT)

    def events[E <: Event](et: EventType[E]): EventStream[E] =
      EventStreams.eventsOf(self, et)

    def clicks: EventStream[MouseEvent] = events(MouseEvent.MOUSE_CLICKED)

    def enters: EventStream[MouseEvent] = events(MouseEvent.MOUSE_ENTERED)

    def exits: EventStream[MouseEvent] = events(MouseEvent.MOUSE_EXITED)

    def presses: EventStream[MouseEvent] = events(MouseEvent.MOUSE_PRESSED)

    def releases: EventStream[MouseEvent] = events(MouseEvent.MOUSE_RELEASED)
  }

  implicit class ObservableValueReactFxOps[A](val self: ObservableValue[A]) extends AnyVal {

    def discards: EventStream[A] = new EventStreamBase[A]() {

      def observeInputs(): Subscription = {
        val listener = new ChangeListener[A] {
          def changed(observable: ObservableValue[_ <: A], oldValue: A, newValue: A): Unit = emit(oldValue)
        }
        self.addListener(listener)
        () =>
          self.removeListener(listener)
      }
    }

    def values: EventStream[A] = EventStreams.valuesOf(self)
  }

  implicit class EventStreamFxOps[A](val self: EventStream[A]) extends AnyVal {

    def collect[U](pf: PartialFunction[A, U]): EventStream[U] = new EventStreamBase[U]() {
      def observeInputs(): Subscription = self.subscribe(t => if (pf.isDefinedAt(t)) emit(pf(t)))
    }
  }

}
