name := "test"

version := "1.0"

scalaVersion := "2.11.8"

resolvers ++= Seq(
  "Spray Repository" at "http://dev.rtmsoft.me/nexus/content/groups/public/")


libraryDependencies ++= Seq(
  "com.wingtech" % "ojdbc" % "6",
  "com.typesafe.akka" %% "akka-http-core" % "2.4.3",
  "com.typesafe.akka" %% "akka-http-spray-json-experimental" % "2.4.3",
  "com.google.code.gson" % "gson" % "2.6.2"

)
