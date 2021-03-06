package com.example.jsontest.rest.test

import org.scalatest.FlatSpec
import org.scalatest.MustMatchers
import java.io.StringWriter
import java.io.PrintWriter
import play.api.libs.json.Json
import play.api.libs.json.Reads
import play.api.libs.json.JsSuccess
import play.api.libs.json.JsPath
import play.api.libs.json.JsError
import play.api.libs.json.Writes
import play.api.libs.json._

object Data {

  case class NestedStructure(
      id: String,
      player1: String,
      player2: String,
      created: Double,
      updated: Double
      ) {

  }

  case class Structure(
      id: String,
      finished: Boolean,
      teams: Map[String,NestedStructure],
      results: Map[String, Double],
      places: Map[String, Int],
      boards: Int,
      tables: Int,
      created: Double,
      updated: Double
  ) {

  }

  implicit val nestedStructureFormat = Json.format[NestedStructure]

  implicit val structureFormat = Json.format[Structure]

  def read[T]( s: String )( implicit reader: Reads[T] ): T = {
    val json = Json.parse(s)
    Json.fromJson[T](json) match {
      case JsSuccess(r, path: JsPath) =>
        r
      case e: JsError =>
        println("Errors: " + JsError.toJson(e).toString())
        throw new JsonException("JSON error: "+JsError.toJson(e).toString())
    }
  }

  def write[T]( t: T )( implicit writer: Writes[T] ): String = {
    val json = Json.toJson(t)
    Json.stringify(json)
  }

  def writePretty[T]( t: T )( implicit writer: Writes[T] ): String = {
    val json = Json.toJson(t)
    Json.prettyPrint(json)
  }

  class JsonException( msg: String) extends Exception(msg)

}

class TestProtocol extends MustMatchers {


  def exceptionToString( ex: Throwable ) = {
    val sw = new StringWriter
    val pw = new PrintWriter(sw)
    ex.printStackTrace(pw)
    pw.flush()
    sw.toString()
  }

//  it should "deserialize and serialize one Structure object" in {
  def test1() = {
    val sw = new StringWriter
    val pw = new PrintWriter(sw)

    try {
      val txt = SampleData.oneStructurePrettyJSON
      import Data._

      pw.println(s"parsing $txt")

      val struct = read[Structure](txt)

      pw.println(s"struct is: ${struct}")

      val json = write(struct)

      pw.println(s"json is $json")

      val res = read[Structure](json)

      pw.println(s"json is  : ${json}")

      res mustBe struct

      pw.println("completed successfully")
    } catch {
      case x: Throwable =>
        val s = exceptionToString(x)
        println( "Caught exception:" )
        println( s )
        // fail("Unexpected exception: "+s)
        throw x
    } finally {
      pw.flush()
      println( sw.toString() )
    }
  }

//  it should "serialize and deserialize one Structure object" in {
  def test2() = {
    val sw = new StringWriter
    val pw = new PrintWriter(sw)

    try {
      import Data._

      val struct = SampleData.sampleData

      pw.println(s"struct is: ${struct}")

      val json = write(struct)

      pw.println(s"json is $json")

      val res = read[Structure](json)

      pw.println(s"json is  : ${json}")

      res mustBe struct

      pw.println("completed successfully")
    } catch {
      case x: Throwable =>
        val s = exceptionToString(x)
        println( "Caught exception:" )
        println( s )
        // fail("Unexpected exception: "+s)
        throw x
    } finally {
      pw.flush()
      println( sw.toString() )
    }
  }

//  it should "serialize and deserialize one Structure object multiple times" in {
  def test3() = {
    var sw = new StringWriter
    var pw = new PrintWriter(sw)

    var count = 0

    try {
      for (i <- 1 to 100) {
        count = i
        val txt = SampleData.oneStructurePrettyJSON
        import Data._

        sw = new StringWriter
        pw = new PrintWriter(sw)

        pw.println(s"iteration $count parsing $txt")

        val struct = SampleData.sampleData

        pw.println(s"struct is: ${struct}")

        val json = write(struct)

        pw.println(s"json is $json")

        val res = read[Structure](json)

        pw.println(s"json is  : ${json}")

        res mustBe struct
      }

      pw.println("completed successfully")
    } catch {
      case x: Throwable =>
        val s = exceptionToString(x)
        pw.println( s"Caught exception on iteration ${count}:" )
        pw.println( s )
        // fail("Unexpected exception: "+s)
        throw x
    } finally {
      pw.flush()
      println( sw.toString() )
    }
  }

//  it should "serialize and deserialize array Structure object" in {
  def test4() = {
    var sw = new StringWriter
    var pw = new PrintWriter(sw)

    try {
      import Data._
      val txt =SampleData.arrayStructure()

      pw.println(s"txt=${txt}")
      val array = read[Array[Structure]](txt)
      pw.println(s"Array=${array}")
      val json = write(array)
      pw.println(s"json=${json}")
      val array2 = read[Array[Structure]](json)

      pw.println(s"Array2=${array2}")

      array mustBe array2

      pw.println("completed successfully")
    } catch {
      case x: Throwable =>
        val s = exceptionToString(x)
        pw.println( "Caught exception:" )
        pw.println( s )
        // fail("Unexpected exception: "+s)
        throw x
    } finally {
      pw.flush()
      println( sw.toString() )
    }
  }

}
object SampleData {
  import Data._
  val sampleData = Structure(
                     "M18",
                     true,
                     Map("T2" -> NestedStructure("T2","Ethan","Ellen",0,1484181936576L),
                         "T1" -> NestedStructure("T1","Nancy","Norman",0,1484181936452L),
                         "T4" -> NestedStructure("T4","Wayne","Wilma",0,1484184889253L),
                         "T3" -> NestedStructure("T3","Sally","Sam",0,1484184920578L)),
                     Map("T2" -> 8.5,
                         "T1" -> 8.5,
                         "T4" -> 8.5,
                         "T3" -> 10.5),
                     Map("T2" -> 2,
                         "T1" -> 2,
                         "T4" -> 2,
                         "T3" -> 1),
                     18,
                     2,
                     1484181113278L,
                     1484191268756L)

    val oneStructurePrettyJSON = """
{
  "id" : "M18",
  "finished" : true,
  "teams" : {
    "T1" : {
      "id" : "T1",
      "player1" : "Nancy",
      "player2" : "Norman",
      "created" : 0,
      "updated" : 1484181936452
    },
    "T2" : {
      "id" : "T2",
      "player1" : "Ethan",
      "player2" : "Ellen",
      "created" : 0,
      "updated" : 1484181936576
    },
    "T3" : {
      "id" : "T3",
      "player1" : "Sally",
      "player2" : "Sam",
      "created" : 0,
      "updated" : 1484184920578
    },
    "T4" : {
      "id" : "T4",
      "player1" : "Wayne",
      "player2" : "Wilma",
      "created" : 0,
      "updated" : 1484184889253
    }
  },
  "results" : {
    "T1" : 8.5,
    "T2" : 8.5,
    "T3" : 10.5,
    "T4" : 8.5
  },
  "places" : {
    "T3" : 1,
    "T4" : 2,
    "T2" : 2,
    "T1" : 2
  },
  "boards" : 18,
  "tables" : 2,
  "created" : 1484181113278,
  "updated" : 1484191268756
}
"""

  def arrayStructure() = {
    val r = 1 to 3
    r.map { i =>
      oneStructurePrettyJSON
    }.mkString("[\n", ",\n", "\n]")
  }

}
