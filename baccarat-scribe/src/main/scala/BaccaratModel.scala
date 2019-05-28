
import better.files.File
import better.files.File._
import customjavafx.scene.control.BeadRoadResult
import fs2.io.fx.{Data, Header}
import javafx.beans.property.{ListProperty, SimpleListProperty, SimpleStringProperty, StringProperty}
import javafx.collections.FXCollections
import javafx.scene.input.KeyCode
import pureconfig.ConfigReader
import pureconfig.generic.auto._

class BaccaratModel {

  val dataDB: File = File(pureconfig.loadConfigOrThrow[String]("game.database.data"))
  val headerDB: File = File(pureconfig.loadConfigOrThrow[String]("game.database.menu"))
  val keysMap: Map[KeyCode, String] = pureconfig.loadConfigOrThrow[Map[KeyCode, String]]("keyboard.keys")
  lazy val coupsMap: Map[String, BeadRoadResult] = pureconfig.loadConfigOrThrow[Map[String, BeadRoadResult]]("keyboard.coups")

  implicit val beadRoadResultReader: ConfigReader[BeadRoadResult] = ConfigReader.fromNonEmptyString(s => Right(BeadRoadResult.valueOf(s)))

  //Load KeyBoard Information from reference.conf
  implicit def keyCodeReader: pureconfig.ConfigReader[Map[KeyCode, String]] = {
    pureconfig.configurable.genericMapReader(s => Right(KeyCode.valueOf(s)))
  }

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
  private val beadRoadList: ListProperty[BeadRoadResult] = new SimpleListProperty(FXCollections.observableArrayList[BeadRoadResult])
  var mediaIndex = 0

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
      dataDB.readDeserialized[Data]()
    } else {
      dataDB.createIfNotExists(asDirectory = false, createParents = true)
      dataDB.writeSerialized(Data(Seq.empty[BeadRoadResult]))
      dataDB.readDeserialized[Data]()
    }
  }

  def loadHeader(): Header = {
    //Load Data From Database
    if (headerDB.exists) {
      headerDB.readDeserialized[Header]()
    } else {
      headerDB.createIfNotExists(asDirectory = false, createParents = true)
      headerDB.writeSerialized(pureconfig.loadConfigOrThrow[Header]("game.menu"))
      headerDB.readDeserialized[Header]()
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

  def getPromoMedia: String = {
    if (mediaCount != 0) {
      getVideoFiles().toArray.apply(mediaIndex)
    }
    else null
  }

  def mediaCount: Int = getVideoFiles(dir = home).size

  def getVideoFiles(dir: File = home): List[String] = {
    dir.list.filter(f => f.extension.contains(".mp4") || f.extension.contains(".avi"))
      .map(f => f.path.toString)
      .toList
  }

  def nextPromoMedia(): Unit = {
    if (mediaCount != 0) {
      mediaIndex += 1
      mediaIndex = mediaIndex % mediaCount
    }
  }

  import scala.collection.JavaConverters._

  def saveData(): Unit = {
    dataDB.writeSerialized(Data(beadRoadList.asScala.toList.filter(x => x != BeadRoadResult.EMPTY)))
  }


}
