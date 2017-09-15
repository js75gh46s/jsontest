//
// To build web stuff for server, into server/target/web/classes/...
//   server/compile:assembly::assembledMappings
//

lazy val commonSettings = Seq(
  organization  := "com.example.jsontest",
  scalaVersion  := "2.12.3",
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

lazy val buildKarma = TaskKey[Unit]("buildKarma")

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
      "com.typesafe.play" %%% "play-json" % "2.6.3" withSources(),
//      "com.typesafe.play" %%% "play-json" % "2.7.0-SNAPSHOT" withSources(),
      
      "org.scalatest" %%% "scalatest" % "3.0.3" % "test" withSources(),
      "org.scalactic" %%% "scalactic" % "3.0.3" withSources()
    )

  ).
  jvmSettings(

  ).
  jsSettings(
    requiresDOM in Test := true,
    skip in packageJSDependencies := false,
    npmDevDependencies in Test ++= Seq(
      "karma" -> "1.7.0",
      "karma-chrome-launcher" -> "2.2.0",
      "karma-scalajs-scalatest" -> "0.1.0"
    ),

    version in webpack := vWebPack,
    version in installJsdom := vJsDom,

    buildKarma := {
      val x = (fastOptJS in Test).value
      val y = (test in Test).value
      val base = baseDirectory.value
      val ctrg = crossTarget.value
      val trg = new File( ctrg, "scalajs-bundler/test" )
      val file = new File(base, "../karma.conf.js").getCanonicalFile
      java.nio.file.Files.copy(file.toPath, new File(trg, file.name).toPath, java.nio.file.StandardCopyOption.REPLACE_EXISTING)
    }

  )

lazy val sharedJS: Project = `jsontest-shared`.js.enablePlugins(ScalaJSBundlerPlugin)
lazy val sharedJVM: Project = `jsontest-shared`.jvm

