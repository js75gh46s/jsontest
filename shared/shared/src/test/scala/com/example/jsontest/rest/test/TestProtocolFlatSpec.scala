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

class TestProtocolFlatSpec extends FlatSpec {

  val t = new TestProtocol
  import t._

  behavior of "Protocol"


  it should "deserialize and serialize one Structure object" in {
    test1()
  }

  it should "serialize and deserialize one Structure object" in {
    test2()
  }

  it should "serialize and deserialize one Structure object multiple times" in {
    test3()
  }

  it should "serialize and deserialize array Structure object" in {
    test4()
  }

  it should "deserialize and serialize one Structure object again" in {
    test1()
  }

}
