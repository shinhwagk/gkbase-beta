package models.gnote.dao

import java.net.{HttpURLConnection, URLConnection}
import javax.print.DocFlavor.URL

import scala.io.Source
import play.api.libs.json._

/**
  * Created by zhangxu on 2016/7/6.
  */
object Test {
//  //      val path = play.Configuration.root().getString("note.doc.content")
//  val path = "https://api.github.com/repos/shinhwagk/gkbase-beta/contents/document";
//
//  def getDoumentSize(docId: Int): Int = {
//    val github_doc_contents_url = s"$path/${docId}/README.md"
//    try {
//      val jsonString = Source.fromURL(github_doc_contents_url, "UTF-8").mkString;
//      (Json.parse(jsonString) \ "size").validate[Int].asOpt.get
//    } catch {
//      case ex: Exception => 0
//    }
//  }
//
//  def judgeDoc(docId: Int): Boolean = {
//    if (getDoumentSize(docId) == 0) false else true
//  }
//
//  def main(args: Array[String]) {
//    val github_doc_contents_url = s"$path/1111111/README.md"
//    val postUrl = new URL(github_doc_contents_url);
//    val connection = asInstanceOf[HttpURLConnection].openConnection();
//
//    //    val code = Source.fromURL(github_doc_contents_url, "UTF-8");
////    println(code.codec)
//  }
}
