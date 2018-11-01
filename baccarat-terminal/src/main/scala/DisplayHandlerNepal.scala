
import java.io.{File => JFile}
import java.util.ResourceBundle

import customjavafx.scene.control._
import customjavafx.scene.layout._
import fs2.io.fx.{Data, Header}
import javafx.beans.value.{ChangeListener, ObservableValue}
import javafx.collections.ObservableList
import javafx.scene.control.{Button, Label, TextField}
import javafx.scene.input.{KeyCode, KeyEvent}
import javafx.scene.layout.{BorderPane, Pane, VBox}
import javafx.scene.media.{Media, MediaPlayer, MediaView}
import scalafx.animation.PauseTransition
import scalafx.geometry.Pos
import scalafx.scene.media.AudioClip
import scalafx.util.Duration
import scalafxml.core.macros.sfxml
import sodium.syntax._

@sfxml(additionalControls = List("customjavafx.scene.control", "customjavafx.scene.layout"))
class DisplayHandlerNepal
(
  val gameBox: VBox,
  val lastWinResultLabel: LastWinResultLabel,
  val tableId: Label,
  val handBetMin: Label,
  val handBetMax: Label,
  val tieBetMin: Label,
  val tieBetMax: Label,
  val pairBetMin: Label,
  val pairBetMax: Label,
  val superBetMin: Label,
  val superBetMax: Label,
  val playerWinCount: Label,
  val bankerWinCount: Label,
  val tieWinCount: Label,
  val playerPairCount: Label,
  val bankerPairCount: Label,
  val naturalCount: Label,
  val totalCount: Label,
  val b1: BigEyeRoadLabel,
  val b2: SmallRoadLabel,
  val b3: CockroachRoadLabel,
  val p1: BigEyeRoadLabel,
  val p2: SmallRoadLabel,
  val p3: CockroachRoadLabel,
  val menu: BorderPane,
  val promoPane: Pane,
  val promoMediaView: MediaView,
  val footing: Label,
  val tTableId: TextField,
  val tHandBetMin: TextField,
  val tHandBetMax: TextField,
  val tTieBetMin: TextField,
  val tTieBetMax: TextField,
  val tPairBetMin: TextField,
  val tPairBetMax: TextField,
  val tSuperSixBetMin: TextField,
  val tSuperSixBetMax: TextField,
  val lTableId: Button,
  val lHandBetMin: Button,
  val lHandBetMax: Button,
  val lTieBetMin: Button,
  val lTieBetMax: Button,
  val lPairBetMin: Button,
  val lPairBetMax: Button,
  val lSuperSixBetMin: Button,
  val lSuperSixBetMax: Button,
  val info: BorderPane,
  val beadRoad: BeadRoadTilePane,
  val bigEyeRoad: BigEyeRoadTilePane,
  val bigEyeRoadDummy: BigEyeRoadDummyTilePane,
  val smallRoad: SmallRoadTilePane,
  val smallRoadDummy: SmallRoadDummyTilePane,
  val cockroachRoad: CockroachRoadTilePane,
  val cockroachRoadDummy: CockroachRoadDummyTilePane,
  val bigRoad: BigRoadTilePane,
  val dynamicResult: BorderPane
)(implicit display: fx.io.Display, res: Option[ResourceBundle]) {

  //Instantiate model
  val model = new BaccaratModel()
  val data: Data = model.loadData()
  val header: Header = model.loadHeader()

  dynamicResult.setVisible(false)

  //Bind the UI Controls -> Model Properties
  tableId.textProperty().bindBidirectional(model.tableIdProperty)
  handBetMin.textProperty().bindBidirectional(model.handBetMinProperty)
  handBetMax.textProperty().bindBidirectional(model.handBetMaxProperty)
  tieBetMin.textProperty().bindBidirectional(model.tieBetMinProperty)
  tieBetMax.textProperty().bindBidirectional(model.tieBetMaxProperty)
  pairBetMin.textProperty().bindBidirectional(model.pairBetMinProperty)
  pairBetMax.textProperty().bindBidirectional(model.pairBetMaxProperty)
  superBetMin.textProperty().bindBidirectional(model.superSixBetMinProperty)
  superBetMax.textProperty().bindBidirectional(model.superSixBetMaxProperty)
  beadRoad.getBeadRoadListProperty.bind(model.beadRoadListProperty)

  beadRoad.Initialize(8, 14)
  bigRoad.Initialize(6, 49)
  bigEyeRoad.Initialize(6, 38)
  bigEyeRoadDummy.Initialize(3, 19)
  smallRoad.Initialize(6, 38)
  smallRoadDummy.Initialize(3, 19)
  cockroachRoad.Initialize(12, 38)
  cockroachRoadDummy.Initialize(6, 19)

  tableId.textProperty().bindBidirectional(tTableId.textProperty())
  handBetMin.textProperty().bindBidirectional(tHandBetMin.textProperty())
  handBetMax.textProperty().bindBidirectional(tHandBetMax.textProperty())
  tieBetMin.textProperty().bindBidirectional(tTieBetMin.textProperty())
  tieBetMax.textProperty().bindBidirectional(tTieBetMax.textProperty())
  pairBetMin.textProperty().bindBidirectional(tPairBetMin.textProperty())
  pairBetMax.textProperty().bindBidirectional(tPairBetMax.textProperty())
  superBetMin.textProperty().bindBidirectional(tSuperSixBetMin.textProperty())
  superBetMax.textProperty().bindBidirectional(tSuperSixBetMax.textProperty())

  tableId.setText(header.tableId)
  handBetMin.setText(header.handBetMin)
  handBetMax.setText(header.handBetMax)
  tieBetMin.setText(header.tieBetMin)
  tieBetMax.setText(header.tieBetMax)
  pairBetMin.setText(header.pairBetMin)
  pairBetMax.setText(header.pairBetMax)
  superBetMin.setText(header.superBetMin)
  superBetMax.setText(header.superBetMax)


  val tList = Array(tTableId, tHandBetMin, tHandBetMax, tTieBetMin, tTieBetMax, tPairBetMin, tPairBetMax, tSuperSixBetMin, tSuperSixBetMax)
  val lList = Array(lTableId, lHandBetMin, lHandBetMax, lTieBetMin, lTieBetMax, lPairBetMin, lPairBetMax, lSuperSixBetMin, lSuperSixBetMax)
  val lastWinPause: PauseTransition = new PauseTransition(Duration(3000))
  var media: Media = null
  var mediaPlayer: MediaPlayer = null
  var menuOn = false
  var infoOn = false
  var editOn = false
  var promoOn = false


  if (java.awt.Toolkit.getDefaultToolkit.getLockingKeyState(java.awt.event.KeyEvent.VK_NUM_LOCK)) {
    menu.toFront()
    lList(mIndex).requestFocus()
    menuOn = true
  }
  var mIndex: Int = 0

  def lastWinUpdates(): Unit = {
    new AudioClip(getClass.getResource(beadRoad.LastWinAudio()).toExternalForm).play()
    lastWinResultLabel.setResult(beadRoad.LastWinResult())
    lastWinResultLabel.setText(res.get.getString(beadRoad.LastWin()))
    lastWinResultLabel.setAlignment(Pos.Center)
    dynamicResult.setVisible(true)
    lastWinPause.stop()
    lastWinPause.play()
  }

  def playPromo(): Unit = {
    if (model.getPromoMedia != null) {
      val f = new JFile(model.getPromoMedia)
      media = new Media(f.toURI.toString)
      mediaPlayer = new MediaPlayer(media)
      mediaPlayer.setCycleCount(-1)
      mediaPlayer.setMute(true)
      promoMediaView.setMediaPlayer(mediaPlayer)
      mediaPlayer.play()
    }
    else {
      println("Error: Promo Videos Not Found in!")
    }
  }

  def stopPromo(): Unit = {
    if (model.getPromoMedia != null) mediaPlayer.dispose()

  }

  beadRoad.getCountProperty
    .addListener(new ChangeListener[Number] {
      override def changed(observableValue: ObservableValue[_ <: Number], t1: Number, t2: Number): Unit = {
        if (t2.intValue() > 0) {
          if (t2.longValue() > t1.longValue()) {
            bigRoad.AddElement(beadRoad)
          } else {
            bigRoad.RemoveElement(beadRoad)
          }
          totalCount.setText(String.valueOf(t2.intValue()))
        } else {
          bigRoad.Reset()
          totalCount.setText("")
        }
        bigRoad.UpdatePredictions(b1, b2, b3, p1, p2, p3)
      }
    })

  beadRoad.getBankerWinCount
    .addListener(new ChangeListener[Number] {
      override def changed(observableValue: ObservableValue[_ <: Number], t1: Number, t2: Number): Unit = {
        if (t2.intValue() > 0) {
          lastWinUpdates()
          bankerWinCount.setText(String.valueOf(t2.intValue()))
        } else {
          bankerWinCount.setText("")
        }
      }
    })

  beadRoad.getPlayerWinCount
    .addListener(new ChangeListener[Number] {
      override def changed(observableValue: ObservableValue[_ <: Number], t1: Number, t2: Number): Unit = {
        if (t2.intValue() > 0) {
          lastWinUpdates()
          playerWinCount.setText(String.valueOf(t2.intValue()))
        } else {
          playerWinCount.setText("")
        }
      }
    })

  beadRoad.getTieWinCount
    .addListener(new ChangeListener[Number] {
      override def changed(observableValue: ObservableValue[_ <: Number], t1: Number, t2: Number): Unit = {
        if (t2.intValue() > 0) {
          lastWinUpdates()
          tieWinCount.setText(String.valueOf(t2.intValue()))
        } else {
          tieWinCount.setText("")
        }
      }
    })

  beadRoad.getBankerPairCount
    .addListener(new ChangeListener[Number] {
      override def changed(observableValue: ObservableValue[_ <: Number], t1: Number, t2: Number): Unit = {
        if (t2.intValue() > 0) {
          bankerPairCount.setText(String.valueOf(t2.intValue()))
        } else {
          bankerPairCount.setText("")
        }
      }
    })

  beadRoad.getPlayerPairCount
    .addListener(new ChangeListener[Number] {
      override def changed(observableValue: ObservableValue[_ <: Number], t1: Number, t2: Number): Unit = {
        if (t2.intValue() > 0) {
          playerPairCount.setText(String.valueOf(t2.intValue()))
        } else {
          playerPairCount.setText("")
        }
      }
    })

  beadRoad.getNaturalCount
    .addListener(new ChangeListener[Number] {
      override def changed(observableValue: ObservableValue[_ <: Number], t1: Number, t2: Number): Unit = {
        if (t2.intValue() > 0) {
          naturalCount.setText(String.valueOf(t2.intValue()))
        } else {
          naturalCount.setText("")
        }
      }
    })

  bigRoad.bigEyeRoadListProperty
    .addListener(new ChangeListener[ObservableList[BigEyeRoadLabel]] {
      override def changed(
                            observableValue: ObservableValue[_ <: ObservableList[BigEyeRoadLabel]],
                            t: ObservableList[BigEyeRoadLabel],
                            t1: ObservableList[BigEyeRoadLabel]): Unit = {
        if (!t1.isEmpty) bigEyeRoad.ReArrangeElements(t1)
        else {
          bigEyeRoad.Reset()
        }
      }
    })

  bigRoad.smallRoadListProperty
    .addListener(new ChangeListener[ObservableList[SmallRoadLabel]] {
      override def changed(
                            observableValue: ObservableValue[_ <: ObservableList[SmallRoadLabel]],
                            t: ObservableList[SmallRoadLabel],
                            t1: ObservableList[SmallRoadLabel]): Unit = {
        if (!t1.isEmpty) smallRoad.ReArrangeElements(t1)
        else {
          smallRoad.Reset()
        }
      }
    })

  bigRoad.cockroachRoadListProperty
    .addListener(new ChangeListener[ObservableList[CockroachRoadLabel]] {
      override def changed(
                            observableValue: ObservableValue[_ <: ObservableList[CockroachRoadLabel]],
                            t: ObservableList[CockroachRoadLabel],
                            t1: ObservableList[CockroachRoadLabel]): Unit = {
        if (!t1.isEmpty) cockroachRoad.ReArrangeElements(t1)
        else {
          cockroachRoad.Reset()
        }
      }
    })


  def focusSame(): Unit = {
    lList(mIndex).requestFocus()
  }

  def focusBack(): Unit = {
    if (mIndex == 0) mIndex = 8
    else {
      mIndex = (mIndex - 1) % 9
    }
    lList(mIndex).requestFocus()
  }


  (display.root
    .handle(KeyEvent.KEY_RELEASED)
    .map(_.getCode)
    .filter(key => model.keysMap.contains(key))
    .transform(Option.empty[String]) {
      case (KeyCode.ENTER, _) if promoOn => stopPromo(); model.nextPromoMedia(); playPromo(); (None, None)
      case (KeyCode.ENTER, _) if menuOn && editOn => lList(mIndex).requestFocus(); editOn = !editOn; (None, None)
      case (KeyCode.ENTER, _) if menuOn => tList(mIndex).requestFocus(); editOn = !editOn; (None, None)
      case (KeyCode.ENTER, result) => gameBox.requestFocus(); (result, None)
      case (KeyCode.NUMPAD2, _) if menuOn && !editOn => focusNext(); (None, None)
      case (KeyCode.NUMPAD8, _) if menuOn && !editOn => focusBack(); (None, None)
      case (KeyCode.NUM_LOCK, _) if menuOn => menu.toBack(); gameBox.requestFocus(); model.saveHeader(); menuOn = false; (None, None)
      case (KeyCode.NUM_LOCK, _) => menu.toFront(); focusSame(); menuOn = true; (None, None)
      case (KeyCode.DIVIDE, _) if promoOn => stopPromo(); promoPane.toBack(); gameBox.requestFocus(); promoOn = false; (None, None)
      case (KeyCode.DIVIDE, _) => promoPane.toFront(); playPromo(); promoPane.requestFocus(); promoOn = true; (None, None)
      case (KeyCode.MULTIPLY, _) if infoOn => info.toBack(); gameBox.requestFocus(); infoOn = false; (None, None)
      case (KeyCode.MULTIPLY, _) => info.toFront(); info.requestFocus(); infoOn = true; (None, None)
      case (key, result) if result.isEmpty => (None, Some(model.keysMap(key)))
      case (key, result) if result.get eq model.keysMap(key) => (None, None)
      case (key, result) if result.get.contains(model.keysMap(key)) => (None, Some(result.get.replaceAll(model.keysMap(key), "")))
      case (key, result) => (None, Some((result.get + model.keysMap(key)).toCharArray.sorted.mkString))
    } unNone)
    .map(model.coupsMap.get)
    .filter(result => result.isDefined)
    .map(result => result.get)
    .foreach { result => {
      result match {
        case BeadRoadResult.EXIT => display.exit()
        case BeadRoadResult.UNDO => beadRoad.RemoveElement()
        case BeadRoadResult.CLEAR => beadRoad.Reset()
        case _ => {
          beadRoad.AddElement(result)
        }
      }
      model.saveData()
    }
    }

  def focusNext(): Unit = {
    mIndex = (mIndex + 1) % 9
    lList(mIndex).requestFocus()
  }

  lastWinPause.setOnFinished {
    e => dynamicResult.setVisible(false)
  }

  //Load the saved results
  data.results.foreach {
    result => beadRoad.AddElement(result)
  }

  footing.setText("Powered By Tykhe Gaming Pvt. Ltd.")


  display.root.setOnCloseRequest(_ => {
    display.exit()
  })
}
