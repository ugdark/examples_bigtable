
name := "examples_bigtable"

version := "0.1"

scalaVersion := "2.12.8"


libraryDependencies += "com.google.cloud.bigtable" % "bigtable-hbase-1.x" % "1.8.0"


lazy val main = (project in file("."))
  .settings(
    assemblyOutputPath in assembly := file(s"${name.value}.jar"),
    assemblyMergeStrategy in assembly := {
      case PathList(ps @ _*) if ps.last endsWith ".properties" => MergeStrategy.first
    }
  )
