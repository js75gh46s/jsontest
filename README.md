# jsontest

This repository is to show a bug with play-json and scala.js.

Uses:
- scala 2.12.2
- scala.js 0.6.19
- play-json 2.6.3
- scalajs bundler 0.6.0

See other dependencies in plugins.sbt and build.sbt

The file [TestProtocol.scala](shared/shared/src/test/scala/com/example/jsontest/rest/test/TestProtocol.scala) contains the failing tests.  The tests work on Windows Chrome and Firefox.  They only fail on iOS Mobile Safari.  The results of the karma tests are:

    Firefox 55.0.0 (Windows 10 0.0.0): Executed 4 of 1 SUCCESS (0 secs / 0.613 secs)
    Chrome 60.0.3112 (Windows 10 0.0.0): Executed 4 of 1 SUCCESS (0.373 secs / 0.359 secs)
    Mobile Safari 9.0.0 (iOS 9.3.5): Executed 4 of 1 (1 FAILED) (1.485 secs / 1.078 secs)
    Mobile Safari 10.0.0 (iOS 10.3.3): Executed 4 of 1 (3 FAILED) (1.311 secs / 0.822 secs)

The error is:

    Mobile Safari 10.0.0 (iOS 10.3.3) com.example.jsontest.rest.test.TestProtocol Protocol should serialize and deserialize one Structure object multiple times FAILED
        scala.scalajs.runtime.UndefinedBehaviorError: An undefined behavior was detected: 5
        at <jscode>.fillInStackTrace__jl_Throwable(http://hostname:9876jsontest-shared-test-fastopt-bundle.js:23896)
        at <jscode>.init___T__jl_Throwable(http://hostname:9876jsontest-shared-test-fastopt-bundle.js:24014)
        at <jscode>.init___T__jl_Throwable(http://hostname:9876jsontest-shared-test-fastopt-bundle.js:49117)
        at <jscode>.init___jl_Throwable(http://hostname:9876jsontest-shared-test-fastopt-bundle.js:49113)
        at <jscode>.$throwArrayIndexOutOfBoundsException(http://hostname:9876jsontest-shared-test-fastopt-bundle.js:304)
        at <jscode>.get(http://hostname:9876jsontest-shared-test-fastopt-bundle.js:939)
        at <jscode>.updated0__O__I__I__O__T2__sci_HashMap$Merger__sci_HashMap(http://hostname:9876jsontest-shared-test-fastopt-bundle.js:70828)
        at <jscode>.$$plus$eq__T2__scm_MapBuilder(http://hostname:9876jsontest-shared-test-fastopt-bundle.js:42568)
        at <jscode>.foreach__F1__V(http://hostname:9876jsontest-shared-test-fastopt-bundle.js:73364)
        at <jscode>.toMap__s_Predef$$less$colon$less__sci_Map(http://hostname:9876jsontest-shared-test-fastopt-bundle.js:58840)
        at <jscode>.value$lzycompute__p1__sc_Map(http://hostname:9876jsontest-shared-test-fastopt-bundle.js:50273)
        at <jscode>.$$bslash$extension1__Lplay_api_libs_json_JsLookupResult__T__Lplay_api_libs_json_JsLookupResult(http://hostname:9876jsontest-shared-test-fastopt-bundle.js:39876)
        at <jscode>.apply__Lplay_api_libs_json_JsValue__sci_List(http://hostname:9876jsontest-shared-test-fastopt-bundle.js:46718)
        at <jscode>.apply__Lplay_api_libs_json_JsValue__sci_List(http://hostname:9876jsontest-shared-test-fastopt-bundle.js:40046)
        at <jscode>.asSingleJsResult__Lplay_api_libs_json_JsValue__Lplay_api_libs_json_JsResult(http://hostname:9876jsontest-shared-test-fastopt-bundle.js:39988)
        at <jscode>.http://hostname:9876jsontest-shared-test-fastopt-bundle.js:1601:96(null:-1)
        at <jscode>.reads__Lplay_api_libs_json_JsValue__Lplay_api_libs_json_JsResult(http://hostname:9876jsontest-shared-test-fastopt-bundle.js:23450)
        at <jscode>.http://hostname:9876jsontest-shared-test-fastopt-bundle.js:1716:84(null:-1)
        at <jscode>.reads__Lplay_api_libs_json_JsValue__Lplay_api_libs_json_JsResult(http://hostname:9876jsontest-shared-test-fastopt-bundle.js:23450)
        at <jscode>.reads__Lplay_api_libs_json_JsValue__Lplay_api_libs_json_JsResult(http://hostname:9876jsontest-shared-test-fastopt-bundle.js:23417)
        at <jscode>.http://hostname:9876jsontest-shared-test-fastopt-bundle.js:1716:84(null:-1)
        at <jscode>.reads__Lplay_api_libs_json_JsValue__Lplay_api_libs_json_JsResult(http://hostname:9876jsontest-shared-test-fastopt-bundle.js:23450)
        at <jscode>.reads__Lplay_api_libs_json_JsValue__Lplay_api_libs_json_JsResult(http://hostname:9876jsontest-shared-test-fastopt-bundle.js:23417)
        at <jscode>.http://hostname:9876jsontest-shared-test-fastopt-bundle.js:1716:84(null:-1)
        at <jscode>.reads__Lplay_api_libs_json_JsValue__Lplay_api_libs_json_JsResult(http://hostname:9876jsontest-shared-test-fastopt-bundle.js:23450)
        at <jscode>.reads__Lplay_api_libs_json_JsValue__Lplay_api_libs_json_JsResult(http://hostname:9876jsontest-shared-test-fastopt-bundle.js:23417)
        at <jscode>.http://hostname:9876jsontest-shared-test-fastopt-bundle.js:1716:84(null:-1)
        at <jscode>.reads__Lplay_api_libs_json_JsValue__Lplay_api_libs_json_JsResult(http://hostname:9876jsontest-shared-test-fastopt-bundle.js:23450)
        at <jscode>.reads__Lplay_api_libs_json_JsValue__Lplay_api_libs_json_JsResult(http://hostname:9876jsontest-shared-test-fastopt-bundle.js:23417)
        at <jscode>.http://hostname:9876jsontest-shared-test-fastopt-bundle.js:1716:84(null:-1)
        at <jscode>.reads__Lplay_api_libs_json_JsValue__Lplay_api_libs_json_JsResult(http://hostname:9876jsontest-shared-test-fastopt-bundle.js:23450)
        at <jscode>.reads__Lplay_api_libs_json_JsValue__Lplay_api_libs_json_JsResult(http://hostname:9876jsontest-shared-test-fastopt-bundle.js:23417)
        at <jscode>.http://hostname:9876jsontest-shared-test-fastopt-bundle.js:1716:84(null:-1)
        
and:

    Mobile Safari 9.0.0 (iOS 9.3.5) com.example.jsontest.rest.test.TestProtocol Protocol should serialize and deserialize one Structure object multiple times FAILED
        scala.scalajs.runtime.UndefinedBehaviorError: An undefined behavior was detected: 798763482
        at <jscode>.fillInStackTrace__jl_Throwable(http://hostname:9876jsontest-shared-test-fastopt-bundle.js:23896)        
        at <jscode>.fillInStackTrace__jl_Throwable(http://hostname:9876jsontest-shared-test-fastopt-bundle.js:49110)        
        at <jscode>.init___T__jl_Throwable(http://hostname:9876jsontest-shared-test-fastopt-bundle.js:24014)
        at <jscode>.init___T__jl_Throwable(http://hostname:9876jsontest-shared-test-fastopt-bundle.js:49117)
        at <jscode>.init___jl_Throwable(http://hostname:9876jsontest-shared-test-fastopt-bundle.js:49113)
        at <jscode>.$throwArrayIndexOutOfBoundsException(http://hostname:9876jsontest-shared-test-fastopt-bundle.js:304)
        at <jscode>.get(http://hostname:9876jsontest-shared-test-fastopt-bundle.js:939)
        at scala.collection.mutable.HashTable.findEntry0(http://hostname:9876jsontest-shared-test-fastopt-bundle.js:16958)
        at scala.collection.mutable.HashTable.findOrAddEntry(http://hostname:9876jsontest-shared-test-fastopt-bundle.js:17057)
        at <jscode>.put__O__O__s_Option(http://hostname:9876jsontest-shared-test-fastopt-bundle.js:73300)
        at <jscode>.$$plus$eq__T2__scm_LinkedHashMap(http://hostname:9876jsontest-shared-test-fastopt-bundle.js:73451)
        at <jscode>.$$plus$eq__O__scg_Growable(http://hostname:9876jsontest-shared-test-fastopt-bundle.js:73355)
        at <jscode>.http://hostname:9876jsontest-shared-test-fastopt-bundle.js:16199:48(null:-1)
        at <jscode>.apply__O__O(http://hostname:9876jsontest-shared-test-fastopt-bundle.js:32156)
        at scala.collection.Iterator.foreach(http://hostname:9876jsontest-shared-test-fastopt-bundle.js:24684)
        at <jscode>.foreach__F1__V(http://hostname:9876jsontest-shared-test-fastopt-bundle.js:62466)
        at scala.collection.generic.Growable.$$plus$plus$eq(http://hostname:9876jsontest-shared-test-fastopt-bundle.js:16197)
        at <jscode>.$$plus$plus$eq__sc_TraversableOnce__scg_Growable(http://hostname:9876jsontest-shared-test-fastopt-bundle.js:72617)
        at <jscode>.apply__sc_Seq__sc_GenMap(http://hostname:9876jsontest-shared-test-fastopt-bundle.js:16131)
        at <jscode>.apply__sc_Seq__Lplay_api_libs_json_JsObject(http://hostname:9876jsontest-shared-test-fastopt-bundle.js:35682)
        at <jscode>.http://hostname:9876jsontest-shared-test-fastopt-bundle.js:23068:92(null:-1)
        at <jscode>.apply__O__O(http://hostname:9876jsontest-shared-test-fastopt-bundle.js:32156)
        at <jscode>.writes__O__Lplay_api_libs_json_JsObject(http://hostname:9876jsontest-shared-test-fastopt-bundle.js:28854)
        at <jscode>.writes__O__Lplay_api_libs_json_JsValue(http://hostname:9876jsontest-shared-test-fastopt-bundle.js:28857)
        at <jscode>.writes__O__Lplay_api_libs_json_JsValue(http://hostname:9876jsontest-shared-test-fastopt-bundle.js:35496)
        at <jscode>.http://hostname:9876jsontest-shared-test-fastopt-bundle.js:1616:59(null:-1)
        at <jscode>.apply__O__O(http://hostname:9876jsontest-shared-test-fastopt-bundle.js:32156)
        at <jscode>.writes__O__Lplay_api_libs_json_JsObject(http://hostname:9876jsontest-shared-test-fastopt-bundle.js:28854)
        at <jscode>.writes__O__Lplay_api_libs_json_JsObject(http://hostname:9876jsontest-shared-test-fastopt-bundle.js:46866)
        at <jscode>.writeFields__scm_Map__Lplay_api_libs_functional_$tilde__V(http://hostname:9876jsontest-shared-test-fastopt-bundle.js:35785)
        at play.api.libs.json.OWrites$OWritesFromFields.writes(http://hostname:9876jsontest-shared-test-fastopt-bundle.js:23354)
        at <jscode>.writes__O__Lplay_api_libs_json_JsObject(http://hostname:9876jsontest-shared-test-fastopt-bundle.js:35823)

## Karma testing

Run:

    sbt jsontest-sharedJS/test:npmUpdate jsontest-sharedJS/test:buildKarma
    cd shared/js/target/scala-2.12/scalajs-bundler/test
    node ./node_modules/karma/bin/karma start karma.conf.js

Then start browser on iPhone or iPad and go to http://hostname:9876
