import java.net.NetworkInterface
import java.util.{Locale, ResourceBundle}

import cats.effect.IO
import fx.io._
import fx.io.syntax._
import javafx.scene.Cursor
import javafx.stage.StageStyle
import scodec.bits.ByteVector

import scala.collection.JavaConverters._


object BaccaratApp extends Display.App {

  def macAddresses: List[String] =
    NetworkInterface.getNetworkInterfaces.asScala.flatMap(i => Option(i.getHardwareAddress)).map(ByteVector(_).toHex).toList


  val mainWindow: IO[Display.Window] = for {
    _ <- IO(println("starting billboard..."))
//    resBundle = Option(ResourceBundle.getBundle("Bundle", new Locale("en", "CA")))
    resBundle = Option(ResourceBundle.getBundle("Bundle", new Locale("zh", "CN")))
    actual  = macAddresses
    expected = List("1c1b0d9c24e0", "e0d55e55809c", "80ce62eb8f72", "70c94e69f961", "80ce62ebc36c",
      "70c94e69c271", "70c94e69c293", "70c94e69c2a3", "70c94e69d0bf", "70c94e69d9b7", "70c94e69ec3f",
      "70c94e69f961", "70c94e69f9df", "70c94e6a073f", "70c94e6c6e45", "80ce62ebc68e", "70c94e69f923",
    "7440bb622e05","7440bb66447b","7440bb651f6b","7440bba3bac9","7440bb6645e7","7440bb65befb","7440bb638e75","7440bb6644ff",
    "509a4c3c9f19")
    _ <- IO(require(actual.exists(expected.contains), "Error: Non Compatible Machine Found!"))
    _ <- IO(println("loading window..."))
  } yield Display.Window(
    fxml = "baccarat-nepal.fxml",
    fullscreen = true,
    alwaysOnTop = true,
    style = StageStyle.UNDECORATED,
    resources = resBundle,
    resolver = resBundle.fxResolver,
    cursor = Cursor.NONE
  )


  override def window: IO[Display.Window] = mainWindow

}