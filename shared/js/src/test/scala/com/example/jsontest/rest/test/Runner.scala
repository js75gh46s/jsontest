package com.example.jsontest.rest.test

import scala.scalajs.js
import scala.scalajs.js.annotation.JSExport
import scala.scalajs.js.annotation.JSExportTopLevel

@JSExportTopLevel("TestRunner")
object Runner {
  @JSExport
  def run() = {
    val t = new TestProtocol

    val r = new Runner

    val ret = js.Array(
      r.runTest(1,t.test1 _),
      r.runTest(2,t.test2 _),
      r.runTest(3,t.test3 _),
      r.runTest(4,t.test4 _),
      r.runTest(5,t.test1 _)
    )

    r.finished()

    ret
  }
}

class Runner {

  var total = 0
  var passed = 0

  def result( i: Int, failed: Boolean, msg: String ) = {
    js.Dynamic.literal(
        "description"->s"Test ${i}",
        "log"-> js.Array( msg ),
        "skipped"-> false,
        "success"-> !failed,
        "suite"-> js.Array( getClass.getName ),
        "id"->(getClass.getName+"."+i)
    )
  }

  def runTest( i: Int, t: ()=>Unit ) = {
    total += 1
    try {
      println(s"Test ${i}")
      t()
      passed += 1
      result(i,false, "success")
    } catch {
      case x: Throwable =>
      result(i,true, s"failed: ${x}")
    }
  }

  def finished() = {
    println(s"Finished passed ${passed} failed ${total-passed} total ${total}")
    if (total != passed) {
//      throw new Exception( s"Failed ${total-passed} tests" )
    }
    js.Dynamic.literal("total"->total,
                       "passed"->passed,
                       "failed"->(total-passed),
                       "message"-> s"Failed ${total-passed} tests")
  }
}
