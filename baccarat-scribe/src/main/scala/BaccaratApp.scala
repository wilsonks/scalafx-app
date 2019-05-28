//import java.net.NetworkInterface
import java.util.{Locale, ResourceBundle}

import cats.effect.IO
import customjavafx.scene.control.BeadRoadResult
import fx.io._
import fx.io.syntax._
import javafx.scene.Cursor
import javafx.stage.StageStyle
import scodec.bits.ByteVector

import scala.collection.JavaConverters._


object BaccaratApp extends Display.App {

//  def macAddresses: List[String] =
//    NetworkInterface.getNetworkInterfaces.asScala.flatMap(i => Option(i.getHardwareAddress)).map(ByteVector(_).toHex).toList


  val mainWindow: IO[Display.Window] = for {
    _ <- IO(println("starting billboard..."))
//        resBundle = Option(ResourceBundle.getBundle("Bundle", new Locale("en", "CA")))
    resBundle = Option(ResourceBundle.getBundle("Bundle", new Locale("zh", "CN")))
//    actual  = macAddresses
//    expected = List("1c1b0d9c24e0", "e0d55e55809c", "80ce62eb8f72", "70c94e69f961", "80ce62ebc36c",
//      "70c94e69c271", "70c94e69c293", "70c94e69c2a3", "70c94e69d0bf", "70c94e69d9b7", "70c94e69ec3f",
//      "70c94e69f961", "70c94e69f9df", "70c94e6a073f", "70c94e6c6e45", "80ce62ebc68e", "70c94e69f923",
//      "7440bb622e05","7440bb66447b","7440bb651f6b","7440bba3bac9","7440bb6645e7","7440bb65befb","7440bb638e75","7440bb6644ff",
//      "509a4c3c9f19","f48e38a3a78c")
//    _ <- IO(require(actual.exists(expected.contains), "Error: Non Compatible Machine Found!"))
    _ <- IO(println("loading window..."))
  } yield Display.Window(
    fxml = "baccarat-nepal.fxml",
    fullscreen = true,
    alwaysOnTop = true,
    style = StageStyle.DECORATED,
    resources = resBundle,
    resolver = resBundle.fxResolver,
    cursor = Cursor.NONE
  )


  override def window: IO[Display.Window] = mainWindow



  import better.files._
  import File._
  import scala.xml.XML

  val dir: File = home/"BaccaratResult"
  val resultFileName: File = home/"BaccaratResult/result.xml"


  val watcher: FileMonitor = new FileMonitor(dir,recursive = true) {
    override def onCreate(file: File, count: Int): Unit = {
      file match  {
        case `resultFileName` => {
          val xmlResult = XML.loadFile(resultFileName.path.toString)
          val gameId = (xmlResult \\ "RESULT" \\ "GAMEID").text
          val winner = (xmlResult \\ "RESULT" \\ "WINNER").text
          val pair = (xmlResult \\ "RESULT" \\ "PAIR").text
          val natural = (xmlResult \\ "RESULT" \\ "NATURAL").text

          val result = (gameId,winner,pair,natural) match {
            case (_,"BANKER","NONE","NO") => BeadRoadResult.BANKER_WIN
            case (_,"BANKER","BANKER","NO") => BeadRoadResult.BANKER_WIN_BANKER_PAIR
            case (_,"BANKER","PLAYER","NO") => BeadRoadResult.BANKER_WIN_PLAYER_PAIR
            case (_,"BANKER","BOTH","NO") => BeadRoadResult.BANKER_WIN_BOTH_PAIR

            case (_,"BANKER","NONE","YES") => BeadRoadResult.BANKER_WIN_NATURAL
            case (_,"BANKER","BANKER","YES") => BeadRoadResult.BANKER_WIN_BANKER_PAIR_NATURAL
            case (_,"BANKER","PLAYER","YES") => BeadRoadResult.BANKER_WIN_PLAYER_PAIR_NATURAL
            case (_,"BANKER","BOTH","YES") => BeadRoadResult.BANKER_WIN_BOTH_PAIR_NATURAL

            case (_,"PLAYER","NONE","NO") => BeadRoadResult.PLAYER_WIN
            case (_,"PLAYER","BANKER","NO") => BeadRoadResult.PLAYER_WIN_BANKER_PAIR
            case (_,"PLAYER","PLAYER","NO") => BeadRoadResult.PLAYER_WIN_PLAYER_PAIR
            case (_,"PLAYER","BOTH","NO") => BeadRoadResult.PLAYER_WIN_BOTH_PAIR

            case (_,"PLAYER","NONE","YES") => BeadRoadResult.PLAYER_WIN_NATURAL
            case (_,"PLAYER","BANKER","YES") => BeadRoadResult.PLAYER_WIN_BANKER_PAIR_NATURAL
            case (_,"PLAYER","PLAYER","YES") => BeadRoadResult.PLAYER_WIN_PLAYER_PAIR_NATURAL
            case (_,"PLAYER","BOTH","YES") => BeadRoadResult.PLAYER_WIN_BOTH_PAIR_NATURAL

            case (_,"TIE","NONE","NO") => BeadRoadResult.TIE_WIN
            case (_,"TIE","BANKER","NO") => BeadRoadResult.TIE_WIN_BANKER_PAIR
            case (_,"TIE","PLAYER","NO") => BeadRoadResult.TIE_WIN_PLAYER_PAIR
            case (_,"TIE","BOTH","NO") => BeadRoadResult.TIE_WIN_BOTH_PAIR

            case (_,"TIE","NONE","YES") => BeadRoadResult.TIE_WIN_NATURAL
            case (_,"TIE","BANKER","YES") => BeadRoadResult.TIE_WIN_BANKER_PAIR_NATURAL
            case (_,"TIE","PLAYER","YES") => BeadRoadResult.TIE_WIN_PLAYER_PAIR_NATURAL
            case (_,"TIE","BOTH","YES") => BeadRoadResult.TIE_WIN_BOTH_PAIR_NATURAL

          }
          println("New Result Added ",result)

        }
      }
    }
  }
  watcher.start()


}