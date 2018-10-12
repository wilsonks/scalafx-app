import java.net.NetworkInterface
import java.util.{Locale, ResourceBundle}

import cats.effect.IO
import fx.io._
import javafx.scene.Cursor
import scodec.bits.ByteVector

import scala.collection.JavaConverters._


object BaccaratApp extends Display.App {

  val mainWindow: IO[Display.Window] = for {
    _ <- IO(println("starting billboard..."))
    actual = macAddresses
    expected = List("1c1b0d9c24e0", "e0d55e55809c", "80ce62eb8f72", "70c94e69f961", "80ce62ebc36c",
      "70c94e69c271", "70c94e69c293", "70c94e69c2a3", "70c94e69d0bf", "70c94e69d9b7", "70c94e69ec3f",
      "70c94e69f961", "70c94e69f9df", "70c94e6a073f", "70c94e6c6e45","80ce62ebc68e","70c94e69f923")
    _ <- IO(require(actual.exists(expected.contains), "Error: Non Compatible Machine Found!"))
    _ <- IO(println("loading window..."))
  } yield Display.Window(
    fxml = "baccarat-nepal.fxml",
    resources = Option(ResourceBundle.getBundle("Bundle", new Locale("zh", "CN"))),
    cursor = Cursor.NONE
  )

  def macAddresses: List[String] =
    NetworkInterface.getNetworkInterfaces.asScala.flatMap(i => Option(i.getHardwareAddress)).map(ByteVector(_).toHex).toList

  override def window: IO[Display.Window] = mainWindow

}