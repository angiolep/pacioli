
name := "pacioli"

organization := "bitspoke"

version := "1.0-SNAPSHOT"


libraryDependencies ++= Seq(
  "joda-time" % "joda-time" % "2.3",
  "org.joda" % "joda-convert" % "1.5",
  "org.scalatest" % "scalatest_2.10" % "2.0.RC2" % "test",
  "org.specs2" %% "specs2" % "2.3.10" % "test"
)

