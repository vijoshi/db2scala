organization := "com.example"

name := "db2scala_anorm"

scalaVersion := "2.11.6"

version := "1.0"

libraryDependencies += "com.typesafe.play" %% "anorm" % "2.3.6"

resolvers ++= Seq(
  "Typesafe Releases" at "http://repo.typesafe.com/typesafe/releases/")
  