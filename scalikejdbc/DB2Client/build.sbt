organization := "com.example"

name := "db2scala_scalikejdbc"

scalaVersion := "2.11.6"

version := "1.0"

libraryDependencies ++= Seq(
      "org.scalikejdbc" %% "scalikejdbc" % "2.2.5",
      "ch.qos.logback"  %  "logback-classic"   % "1.1.2"
    )
    
    