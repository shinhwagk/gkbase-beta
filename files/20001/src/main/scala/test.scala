import java.sql.DriverManager
import java.util

import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.http.scaladsl.model.headers._
import akka.http.scaladsl.model.{ContentTypes, HttpEntity, HttpResponse}
import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.server.directives.RespondWithDirectives
import akka.stream.ActorMaterializer
import com.google.gson.GsonBuilder
import akka.http.scaladsl.model.HttpMethods._
import akka.http.scaladsl.server.directives.RespondWithDirectives

//trait EnableCORSDirectives extends RespondWithDirectives {
//
//  private val allowedCorsVerbs = List(
//    CONNECT, DELETE, GET, HEAD, OPTIONS,
//    PATCH, POST, PUT, TRACE
//  )
//
//  private val allowedCorsHeaders = List(
//    "X-Requested-With", "content-type", "origin", "accept"
//  )
//
//  lazy val enableCORS =
//    respondWithHeader(`Access-Control-Allow-Origin`.`*`) &
//      respondWithHeader(`Access-Control-Allow-Methods`(allowedCorsVerbs)) &
//      respondWithHeader(`Access-Control-Allow-Headers`(allowedCorsHeaders)) &
//      respondWithHeader(`Access-Control-Allow-Credentials`(true))
//
//}
//

object WebServer {
  implicit val system = ActorSystem("my-system")
  implicit val materializer = ActorMaterializer()
  implicit val executionContext = system.dispatcher

  val route =
    path("hello") {
      get {
        //        RespondWithDirectives(RawHeader(`Access-Control-Allow-Origin`.`*`)) {
        //        complete(HttpResponse(200).withHeaders(`Access-Control-Allow-Origin`.*).withEntity("{\"a\":\"b\"}"))
        complete(HttpEntity(ContentTypes.`application/json`,""" [ { "name": "Donuts" },{ "name": "Pizza" },{ "name": "Sushi" }]"""))
      }
    }


  def main(args: Array[String]) {
    Http().bindAndHandle(route, "localhost", 8080)
  }

  def getJson = {
    Class.forName("oracle.jdbc.driver.OracleDriver");
    val conn = DriverManager.getConnection("jdbc:oracle:thin:@10.65.193.22:1521/whdb2", "gtest", "gtest");
    val stmt = conn.createStatement()
    val rs = stmt.executeQuery("select 'adfasdf'||level as \"name\" from dual connect by level <= 10")
    val colCnt = rs.getMetaData.getColumnCount
    val gson = new GsonBuilder().enableComplexMapKeySerialization().create();
    val rsMap = new util.HashMap[String, String]()
    while (rs.next()) {
      for (i <- 1 to colCnt) {
        val name = rs.getMetaData.getColumnName(i)
        rsMap.put(name, rs.getString(name))
      }
    }
    rs.close()
    stmt.close()
    conn.close()
    gson.toJson(rsMap)
  }
}