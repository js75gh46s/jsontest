//
// To build web stuff for server, into server/target/web/classes/...
//   server/compile:assembly::assembledMappings
//

scalaVersion  := "2.12.4"

lazy val commonSettings = Seq(
  organization  := "com.example.jsontest",
  scalaVersion  := "2.12.4",
  scalacOptions := Seq(
    "-unchecked" 
    ,"-deprecation"
    ,"-encoding"
    ,"utf8"
    ,"-feature"
//    ,"-Ymacro-debug-verbose"
//    ,"-Xlog-implicits"
  ),
  EclipseKeys.withSource := true
)

val Node = new scalajsbundler.ExternalCommand("node")

lazy val buildKarma = TaskKey[Unit]("buildKarma")
lazy val karma = TaskKey[Unit]("karma")
lazy val karmaSingle = TaskKey[Unit]("karmaSingle")
lazy val runKarma = TaskKey[Unit]("runKarma")
lazy val runKarmaSingle = TaskKey[Unit]("runKarmaSingle")

lazy val vWebPack = "2.6.1"          // https://www.npmjs.com/package/webpack
lazy val vJsDom = "9.12.0"           // https://www.npmjs.com/package/jsdom

lazy val jsontest = project.in(file(".")).
  aggregate( sharedJS, sharedJVM )
  
lazy val `jsontest-shared` = crossProject.in(file("shared")).
  settings(commonSettings: _*).
  settings(
    name := "jsontest-shared",
    resolvers += Resolver.bintrayRepo("scalaz", "releases"),

//    testOptions in Test += Tests.Filter(s => { println("TestOption: "+s); true })
    EclipseKeys.useProjectId := true,

    libraryDependencies ++= Seq(
      "com.typesafe.play" %%% "play-json" % "2.6.7" withSources(),
//      "com.typesafe.play" %%% "play-json" % "2.7.0-SNAPSHOT" withSources(),
      
      "org.scalatest" %%% "scalatest" % "3.0.4" % "test" withSources(),
      "org.scalactic" %%% "scalactic" % "3.0.4" withSources()
    )

  ).
  jvmSettings(

  ).
  jsSettings(
    requiresDOM in Test := true,
//    jsEnv := new org.scalajs.jsenv.jsdomnodejs.JSDOMNodeJSEnv,
    skip in packageJSDependencies := false,
    npmDependencies in Test ++= Seq(
//      "jsdom" -> "11.4.0"
    ),
    npmDevDependencies in Test ++= Seq(
      "karma" -> "1.7.0",
      "karma-chrome-launcher" -> "2.2.0",
      "karma-custom" -> "1.1.9"
    ),

//    version in webpack := vWebPack,
//    version in installJsdom := vJsDom,

    buildKarma in Test := {
      val x = (fastOptJS in Test).value
      val y = (test in Test).value
      val base = baseDirectory.value
      val ctrg = crossTarget.value
      val trg = new File( ctrg, "scalajs-bundler/test" )
      val file = new File(base, "../karma.conf.js").getCanonicalFile ::
                 new File(base, "../testmain.js").getCanonicalFile ::
                 Nil
      file.foreach { f => 
        java.nio.file.Files.copy(f.toPath, new File(trg, f.name).toPath, java.nio.file.StandardCopyOption.REPLACE_EXISTING)
      }
    },

    karma in Test := {
      val log = streams.value.log
      val base = baseDirectory.value
      val workdir = new File(base, "target/scala-2.12/scalajs-bundler/test").getCanonicalFile

      Node.run("./node_modules/karma/bin/karma", "start", "karma.conf.js")(workdir,log)      
    },

    karmaSingle in Test := {
      val log = streams.value.log
      val base = baseDirectory.value
      val workdir = new File(base, "target/scala-2.12/scalajs-bundler/test").getCanonicalFile

      Node.run("./node_modules/karma/bin/karma", "start", "karma.conf.js", "--single-run")(workdir,log)      
    },

    runKarma in Test := Def.sequential( buildKarma in Test, karma in Test).value,
    runKarmaSingle in Test := Def.sequential( buildKarma in Test, karmaSingle in Test ).value

  )

lazy val sharedJS: Project = `jsontest-shared`.js.enablePlugins(ScalaJSBundlerPlugin)
lazy val sharedJVM: Project = `jsontest-shared`.jvm

