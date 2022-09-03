val scala3Version = "3.1.3"

lazy val root = project
  .in(file("."))
  .settings(
    name := "scala3_test1",
    version := "0.1.0-SNAPSHOT",

    scalaVersion := scala3Version,

    libraryDependencies += "org.scalameta" %% "munit" % "0.7.29" % Test,
    libraryDependencies += ("org.scala-lang.modules" %% "scala-parser-combinators" % "2.1.1").cross(CrossVersion.for3Use2_13)

  )
