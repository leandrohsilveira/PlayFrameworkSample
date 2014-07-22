import sbt._
import Keys._
import play.Project._

object ApplicationBuild extends Build {

  val appName         = "TesteSublime"
  val appVersion      = "1.0-SNAPSHOT"

  val appDependencies = Seq(
    // Add your project dependencies here,
    javaCore,
    javaJdbc,
    javaEbean,
    "mysql" % "mysql-connector-java" % "5.1.30", jdbc, anorm
  )

  val main = play.Project(appName, appVersion, appDependencies).settings(
    // Add your own project settings here
  )

  coffeescriptOptions := Seq("bare")
}
