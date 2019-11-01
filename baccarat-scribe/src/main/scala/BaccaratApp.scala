import java.nio.file.{Path, Paths, WatchEvent}
import java.util.{Locale, ResourceBundle}

import cats.effect.{ExitCode, IO, IOApp}
import com.sun.nio.file.SensitivityWatchEventModifier
import customjavafx.scene.control.BeadRoadResult
import fs2.io.Watcher
import fx.io.Display.{Bounds, Dimension, Position}
import fx.io._
import fx.io.syntax._

import javafx.scene.Cursor
import javafx.stage.StageStyle
import host.SecureApp

import scala.xml.XML

object BaccaratApp extends IOApp with Display.App with SecureApp {

  def watch(path: Path): fs2.Stream[IO, BeadRoadResult] =
    fs2.io.file
      .watch[IO](
      path,
      types = List(Watcher.EventType.Created, Watcher.EventType.Modified),
      modifiers = List(SensitivityWatchEventModifier.HIGH))
      .collect {
        case Watcher.Event.Created(p, _)  => p
        case Watcher.Event.Modified(p, _) => p
      }
      .evalMap(parse)

  def parse(path: Path): IO[BeadRoadResult] =
    IO {
      val root = XML.loadFile(path.toFile)
      val gameId = (root \\ "RESULT" \\ "GAMEID").text
      val winner = (root \\ "RESULT" \\ "WINNER").text
      val pair = (root \\ "RESULT" \\ "PAIR").text
      val natural = (root \\ "RESULT" \\ "NATURAL").text

      (gameId, winner, pair, natural) match {
        case (_, "BANKER", "NONE", "NO")   => BeadRoadResult.BANKER_WIN
        case (_, "BANKER", "BANKER", "NO") => BeadRoadResult.BANKER_WIN_BANKER_PAIR
        case (_, "BANKER", "PLAYER", "NO") => BeadRoadResult.BANKER_WIN_PLAYER_PAIR
        case (_, "BANKER", "BOTH", "NO")   => BeadRoadResult.BANKER_WIN_BOTH_PAIR

        case (_, "BANKER", "NONE", "YES")   => BeadRoadResult.BANKER_WIN_NATURAL
        case (_, "BANKER", "BANKER", "YES") => BeadRoadResult.BANKER_WIN_BANKER_PAIR_NATURAL
        case (_, "BANKER", "PLAYER", "YES") => BeadRoadResult.BANKER_WIN_PLAYER_PAIR_NATURAL
        case (_, "BANKER", "BOTH", "YES")   => BeadRoadResult.BANKER_WIN_BOTH_PAIR_NATURAL

        case (_, "PLAYER", "NONE", "NO")   => BeadRoadResult.PLAYER_WIN
        case (_, "PLAYER", "BANKER", "NO") => BeadRoadResult.PLAYER_WIN_BANKER_PAIR
        case (_, "PLAYER", "PLAYER", "NO") => BeadRoadResult.PLAYER_WIN_PLAYER_PAIR
        case (_, "PLAYER", "BOTH", "NO")   => BeadRoadResult.PLAYER_WIN_BOTH_PAIR

        case (_, "PLAYER", "NONE", "YES")   => BeadRoadResult.PLAYER_WIN_NATURAL
        case (_, "PLAYER", "BANKER", "YES") => BeadRoadResult.PLAYER_WIN_BANKER_PAIR_NATURAL
        case (_, "PLAYER", "PLAYER", "YES") => BeadRoadResult.PLAYER_WIN_PLAYER_PAIR_NATURAL
        case (_, "PLAYER", "BOTH", "YES")   => BeadRoadResult.PLAYER_WIN_BOTH_PAIR_NATURAL

        case (_, "TIE", "NONE", "NO")   => BeadRoadResult.TIE_WIN
        case (_, "TIE", "BANKER", "NO") => BeadRoadResult.TIE_WIN_BANKER_PAIR
        case (_, "TIE", "PLAYER", "NO") => BeadRoadResult.TIE_WIN_PLAYER_PAIR
        case (_, "TIE", "BOTH", "NO")   => BeadRoadResult.TIE_WIN_BOTH_PAIR

        case (_, "TIE", "NONE", "YES")   => BeadRoadResult.TIE_WIN_NATURAL
        case (_, "TIE", "BANKER", "YES") => BeadRoadResult.TIE_WIN_BANKER_PAIR_NATURAL
        case (_, "TIE", "PLAYER", "YES") => BeadRoadResult.TIE_WIN_PLAYER_PAIR_NATURAL
        case (_, "TIE", "BOTH", "YES")   => BeadRoadResult.TIE_WIN_BOTH_PAIR_NATURAL
        case _ => {
          println(s"Bad Input=> Winner=$winner Pair=$pair, Natural=$natural")
          BeadRoadResult.EMPTY
        }
      }
    }

  override def run(args: List[String]): IO[ExitCode] = for {
    _ <- verifyHost[IO]
    _ <- IO(println("starting billboard..."))
    resBundle = Option(ResourceBundle.getBundle("Bundle", new Locale("zh", "CN")))
    //    resBundle = Option(ResourceBundle.getBundle("Bundle", new Locale("zh", "CN")))
    reader <- watch(Paths.get(pureconfig.loadConfigOrThrow[String]("result"))).fxReader

    window = Display.Window(
      fxml = "baccarat-nepal-cmg.fxml",
      position = Position(Some(pureconfig.loadConfigOrThrow[Double]("window.position.x")),
        Some(pureconfig.loadConfigOrThrow[Double]("window.position.y"))),
      bounds = Bounds(width = Dimension(exact = Some(pureconfig.loadConfigOrThrow[Double]("window.width.exact"))),
        height = Dimension(exact = Some(pureconfig.loadConfigOrThrow[Double]("window.height.exact")))),
      fullscreen = false,
      alwaysOnTop = true,
      style = StageStyle.UNDECORATED,
      resources = resBundle,
      resolver = resBundle.fxResolver ++ reader.fxResolver,
      cursor = Cursor.NONE
    )
    _ <- launch(window)(args)
  } yield ExitCode.Success


}

