
import java.util.ArrayList

import better.files.{File, FileMonitor}
import File._
import customjavafx.scene.control.BeadRoadResult
import fs2.io.fx.{Data, Header, Promo}
import javafx.beans.property.{ListProperty, SimpleListProperty, SimpleStringProperty, StringProperty}
import javafx.collections.FXCollections
import javafx.scene.input.KeyCode

class BaccaratModel {

  val dataDB: File = File(pureconfig.loadConfigOrThrow[String]("game.database.data"))
  val headerDB: File = File(pureconfig.loadConfigOrThrow[String]("game.database.menu"))
  var mediaIndex = 0
  def mediaCount: Int = getVideoFiles(dir = home).size

  //Load KeyBoard Information from application.conf
  implicit def keyCodeReader: pureconfig.ConfigReader[Map[KeyCode, String]] = {
    pureconfig.ConfigReader.deriveMap[String].map(_.map {
      case (k, v) => (KeyCode.valueOf(k), v)
    })
  }

  implicit def BeadRoadResultReader: pureconfig.ConfigReader[Map[String, BeadRoadResult]] = {
    pureconfig.ConfigReader.deriveMap[String].map(_.map {
      case (k, v) => (k, BeadRoadResult.valueOf(v))
    })
  }

  val keysMap: Map[KeyCode, String] = pureconfig.loadConfigOrThrow[Map[KeyCode, String]]("keyboard.keys")
  val coupsMap: Map[String, BeadRoadResult] = pureconfig.loadConfigOrThrow[Map[String, BeadRoadResult]]("keyboard.coups")


  //Define Data Elements & Getter Methods
  private val tableId: StringProperty = new SimpleStringProperty("")
  private val handBetMin: StringProperty = new SimpleStringProperty("")
  private val handBetMax: StringProperty = new SimpleStringProperty("")
  private val tieBetMin: StringProperty = new SimpleStringProperty("")
  private val tieBetMax: StringProperty = new SimpleStringProperty("")
  private val pairBetMin: StringProperty = new SimpleStringProperty("")
  private val pairBetMax: StringProperty = new SimpleStringProperty("")
  private val superSixBetMin: StringProperty = new SimpleStringProperty("")
  private val superSixBetMax: StringProperty = new SimpleStringProperty("")
  private val beadRoadList: ListProperty[BeadRoadResult] = new SimpleListProperty[BeadRoadResult](FXCollections.observableList(new ArrayList[BeadRoadResult]))

  def tableIdProperty: StringProperty = tableId

  def handBetMinProperty: StringProperty = handBetMin

  def handBetMaxProperty: StringProperty = handBetMax

  def tieBetMinProperty: StringProperty = tieBetMin

  def tieBetMaxProperty: StringProperty = tieBetMax

  def pairBetMinProperty: StringProperty = pairBetMin

  def pairBetMaxProperty: StringProperty = pairBetMax

  def superSixBetMinProperty: StringProperty = superSixBetMin

  def superSixBetMaxProperty: StringProperty = superSixBetMax

  def beadRoadListProperty: ListProperty[BeadRoadResult] = beadRoadList

  //Load Data From Database
  def loadData(): Data = {
    if (dataDB.exists) {
      dataDB.readDeserialized[Data]
    } else {
      dataDB.createIfNotExists(asDirectory = false, createParents = true)
      dataDB.writeSerialized(Data(Seq.empty[BeadRoadResult]))
      dataDB.readDeserialized[Data]
    }
  }

  def loadHeader(): Header = {
    //Load Data From Database
    if (headerDB.exists) {
      headerDB.readDeserialized[Header]
    } else {
      headerDB.createIfNotExists(asDirectory = false, createParents = true)
      headerDB.writeSerialized(pureconfig.loadConfigOrThrow[Header]("game.menu"))
      headerDB.readDeserialized[Header]
    }
  }


  def saveHeader(): Unit = {
    headerDB.writeSerialized(
      Header(
        tableId.get(),
        handBetMin.get(),
        handBetMax.get(),
        tieBetMin.get(),
        tieBetMax.get(),
        pairBetMin.get(),
        pairBetMax.get(),
        superSixBetMin.get(),
        superSixBetMax.get()
      ))
  }

  def getVideoFiles(dir:File = home):List[String] = {
    dir.list.filter(f => f.extension == Some(".mp4") || f.extension == Some(".avi"))
      .map(f => f.path.toString)
      .toList
  }

  def getVideoFilesRec(dir:File = home):List[String] = {
    dir.listRecursively.filter(f => f.extension == Some(".mp4") || f.extension == Some(".avi"))
      .map(f => f.path.toString)
      .toList
  }


  def getPromoMedia: String = {
    if(mediaCount != 0) {
      getVideoFiles().toArray.apply(mediaIndex)
    }
    else null
  }

  def nextPromoMedia():Unit = {
    if (mediaCount != 0) {
      mediaIndex += 1
      mediaIndex = mediaIndex % mediaCount
    }
  }

  import scala.collection.JavaConverters._

  def saveData():Unit = {
    dataDB.writeSerialized(Data(beadRoadList.asScala.toList.filter(x => x != BeadRoadResult.EMPTY)))
  }

  val watchUSBInsert: FileMonitor = new FileMonitor(File("/media/"),recursive = true) {
    override def onCreate(file: File, count: Int):Unit = {
      println(s"$file got created")
      getVideoFilesRec(file).foreach(println)
    }
  }

  import scala.concurrent.ExecutionContext.Implicits.global

  watchUSBInsert.start()

}
