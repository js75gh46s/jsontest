
// To check the versions of the plugins, issue the following command:
//
//    sbt "reload plugins" dependencyUpdates
//

name := "project-jsontest"

val vLog4j = "1.7.25"              // https://mvnrepository.com/artifact/org.slf4j/slf4j-simple
val vJGit = "4.8.0.201706111038-r" // https://mvnrepository.com/artifact/org.eclipse.jgit/org.eclipse.jgit.pgm

val vScalaJS = "0.6.19"            // http://www.scala-js.org/
//val vScalaJS = "1.0.0-M1"            // http://www.scala-js.org/
val vSbtAssembly = "0.14.5"        // https://github.com/sbt/sbt-assembly
val vSbtGit = "0.9.3"              // https://github.com/sbt/sbt-git
val vSbtSCoverage = "1.5.0"        // https://github.com/scoverage/sbt-scoverage
val vSbtBuildInfo = "0.7.0"        // https://github.com/sbt/sbt-buildinfo
val vSbtRelease = "1.0.5"          // https://github.com/sbt/sbt-release
val vSbtEclipse = "5.1.0"          // https://github.com/typesafehub/sbteclipse

val vSbtGzip = "1.0.1"             // https://github.com/sbt/sbt-gzip
val vSbtScalaJsBundler = "0.6.0"   // https://github.com/scalacenter/scalajs-bundler

// not used:

val vSbtRevolver = "0.8.0"         // https://github.com/spray/sbt-revolver
val vSbtUniDoc = "0.3.3"           // https://github.com/sbt/sbt-unidoc
val vSbtDigest = "1.1.1"           // https://github.com/sbt/sbt-digest

// The following is needed to get rid of the message
//   SLF4J: Failed to load class "org.slf4j.impl.StaticLoggerBinder".
// when sbt is started.

libraryDependencies += "org.slf4j" % "slf4j-log4j12" % vLog4j

// Unfortunately this causes an exception to be logged to the console from sbt-git plugin
// because it can't find git.
// update jgit plugin to avoid exception

libraryDependencies ++= Seq(
  "org.eclipse.jgit" % "org.eclipse.jgit.pgm" % vJGit
     exclude("javax.jms", "jms")
     exclude("com.sun.jdmk", "jmxtools")
     exclude("com.sun.jmx", "jmxri")
)

// addSbtPlugin("io.spray" % "sbt-revolver" % vSbtRevolver)
addSbtPlugin("org.scala-js" % "sbt-scalajs" % vScalaJS)
addSbtPlugin("com.eed3si9n" % "sbt-assembly" % vSbtAssembly)
addSbtPlugin("com.typesafe.sbt" % "sbt-git" % vSbtGit)
addSbtPlugin("org.scoverage" % "sbt-scoverage" % vSbtSCoverage)
addSbtPlugin("com.eed3si9n" % "sbt-buildinfo" % vSbtBuildInfo)
addSbtPlugin("com.github.gseitz" % "sbt-release" % vSbtRelease)
addSbtPlugin("com.typesafe.sbteclipse" % "sbteclipse-plugin" % vSbtEclipse)

scalacOptions ++= Seq( "-unchecked", "-deprecation" )

scalacOptions ++= Seq( "-unchecked", "-feature", "-deprecation" )

// addSbtPlugin("com.eed3si9n" % "sbt-unidoc" % vSbtUniDoc)

addSbtPlugin("com.typesafe.sbt" % "sbt-gzip" % vSbtGzip)

// addSbtPlugin("com.typesafe.sbt" % "sbt-digest" % vSbtDigest)

addSbtPlugin("ch.epfl.scala" % "sbt-web-scalajs-bundler" % vSbtScalaJsBundler)
addSbtPlugin("ch.epfl.scala" % "sbt-scalajs-bundler" % vSbtScalaJsBundler)

