import java.sql.DriverManager
import java.util

import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.http.scaladsl.model.headers.{Host, HttpOrigin, `Access-Control-Allow-Origin`}
import akka.http.scaladsl.model.{ContentTypes, HttpResponse}
import akka.http.scaladsl.server.Directives._
import akka.stream.ActorMaterializer
import com.google.gson.GsonBuilder

/**
  * Created by zhangxu on 2016/4/14.
  */
object nimabi {


  implicit val system = ActorSystem("my-system")
  implicit val materializer = ActorMaterializer()
  implicit val executionContext = system.dispatcher

  val route =
    path("hello") {
      get {
//        Thread.sleep(3000)
        complete(HttpResponse(200).withHeaders(
          `Access-Control-Allow-Origin`(HttpOrigin("http", Host("127.0.0.1", 3000)))
//        ).withEntity(ContentTypes.`application/json`, """[ { "name": "Donuts" , "alias": "Pizza" },{ "name": "Sushi","alias":"bbbb"}]"""))
        ).withEntity(ContentTypes.`application/json`, getJson))
      }
    }

  def main(args: Array[String]) {
    getJson
    Http().bindAndHandle(route, "localhost", 8080)
  }

  def getJson = {
    val sql = "select * from dba_tables where rownum < 50 "
    val hcsql = s"select * from (${sql}) where rownum<=10"
    Class.forName("oracle.jdbc.driver.OracleDriver");
    val conn = DriverManager.getConnection("jdbc:oracle:thin:@10.65.193.22:1521/whdb2", "gtest", "gtest");
    val stmt = conn.createStatement()
    val rs = stmt.executeQuery(sql)
    val colCnt = rs.getMetaData.getColumnCount
    val gson = new GsonBuilder().enableComplexMapKeySerialization().create();
    val rowSet = new util.ArrayList[java.util.HashMap[String, String]]()
    while (rs.next()) {
      val rsMap = new java.util.HashMap[String, String]()
      for (i <- 1 to colCnt) {
        val name = rs.getMetaData.getColumnName(i)
        rsMap.put(name, rs.getString(name))
      }
      rowSet.add(rsMap)
    }
    rs.close()
    stmt.close()
    conn.close()
    gson.toJson(rowSet)
  }
}
