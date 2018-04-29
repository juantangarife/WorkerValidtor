
name := """WorkerValidator"""
organization := "com.arquisoft.grupo4"

version := "1.0-SNAPSHOT"

lazy val root = (project in file("."))
  .enablePlugins(PlayJava, PlayEbean)

resolvers += "scalaz-bintray" at "https://dl.bintray.com/scalaz/releases"

scalaVersion := "2.12.4"

crossScalaVersions := Seq("2.11.12", "2.12.4")

testOptions in Test := Seq(Tests.Argument(TestFrameworks.JUnit, "-a", "-v"))

libraryDependencies ++= Seq(
  javaJdbc,
  javaWs,
  evolutions,
  guice,
  "org.postgresql" % "postgresql" % "42.2.2",
  "com.amazonaws" % "aws-java-sdk-sqs" % "1.11.301"
)
playEbeanDebugLevel := 4