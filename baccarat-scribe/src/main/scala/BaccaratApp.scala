import java.util.{Locale, ResourceBundle}

import cats.effect.{ExitCode, IO, IOApp}
import fx.io._
import fx.io.syntax._
import host.SecureApp
import javafx.scene.Cursor
import javafx.stage.StageStyle

object BaccaratApp extends IOApp with Display.App with SecureApp {

  override def run(args: List[String]): IO[ExitCode] = for {
    _ <- verifyHost[IO]
    _ <- IO(println("starting billboard..."))
    resBundle = Option(ResourceBundle.getBundle("Bundle", new Locale("en", "CA")))
//    resBundle = Option(ResourceBundle.getBundle("Bundle", new Locale("zh", "CN")))
    window = Display.Window(
      fxml = "baccarat.fxml",
      fullscreen = true,
      alwaysOnTop = true,
      style = StageStyle.UNDECORATED,
      resources = resBundle,
      resolver = resBundle.fxResolver,
      cursor = Cursor.NONE
    )
    _ <- launch(window)(args)
  } yield ExitCode.Success

}