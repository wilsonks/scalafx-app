import java.net.NetworkInterface
import java.nio.file.{Path, Paths, WatchEvent}
import java.util.{Locale, ResourceBundle}

import cats.effect.IO
import com.sun.nio.file.SensitivityWatchEventModifier
import customjavafx.scene.control.BeadRoadResult
import fs2.io.Watcher
import fx.io.Display.{Bounds, Dimension, Position}
import fx.io._
import fx.io.syntax._
import javafx.scene.Cursor
import javafx.stage.StageStyle
import scodec.bits.ByteVector

import scala.collection.JavaConverters._
import scala.xml.XML

object BaccaratApp extends Display.App {

  def macAddresses: List[String] =
    NetworkInterface.getNetworkInterfaces.asScala.flatMap(i => Option(i.getHardwareAddress)).map(ByteVector(_).toHex).toList

  val mainWindow: IO[Display.Window] = for {
    _ <- IO(println("starting billboard..."))
//        resBundle = Option(ResourceBundle.getBundle("Bundle", new Locale("en", "CA")))
    resBundle = Option(ResourceBundle.getBundle("Bundle", new Locale("zh", "CN")))
    actual  = macAddresses
    expected = List("1c1b0d9c24e0", "e0d55e55809c", "80ce62eb8f72", "70c94e69f961", "80ce62ebc36c",
      "70c94e69c271", "70c94e69c293", "70c94e69c2a3", "70c94e69d0bf", "70c94e69d9b7", "70c94e69ec3f",
      "70c94e69f961", "70c94e69f9df", "70c94e6a073f", "70c94e6c6e45", "80ce62ebc68e", "70c94e69f923",
      "7440bb622e05","7440bb66447b","7440bb651f6b","7440bba3bac9","7440bb6645e7","7440bb65befb","7440bb638e75","7440bb6644ff",
      "509a4c3c9f19","f48e38a3a78c","94c691a8e991","f439092ca4b7","52540018fb65")
    _ <- IO(require(actual.exists(expected.contains), "Error: Non Compatible Machine Found!"))
    _ <- IO(println("loading window..."))
    reader <- watch(Paths.get(pureconfig.loadConfigOrThrow[String]("result"))).fxReader
  } yield
    Display.Window(
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

  override def window: IO[Display.Window] = mainWindow



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
      }
    }
}
