
name := "examples_bigtable"

version := "0.1"

scalaVersion := "2.12.8"


libraryDependencies += "com.google.cloud.bigtable" % "bigtable-hbase-1.x" % "1.8.0"

lazy val main = (project in file("."))
  .settings(
   assemblyMergeStrategy in assembly := {
      case PathList("META-INF", "io.netty.versions.properties") => MergeStrategy.discard
      case PathList(ps @ _*) if ps.last endsWith ".class" => MergeStrategy.last
      case x =>
        val oldStrategy = (assemblyMergeStrategy in assembly).value
        oldStrategy(x)
    }
  )
