package models.gnote.dao

import scala.io.Source
import play.api.libs.json._

/**
  * Created by zhangxu on 2016/7/6.
  */
object Test {
    val path = play.Configuration.root().getString("note.doc.content")
//  val path = "https://api.github.com/repos/shinhwagk/gkbase-beta/contents/document";

  def getDoumentSize(docId: Int): Int = {
    val github_doc_contents_url = s"$path/${docId}/README.md"
    println(github_doc_contents_url)
    val jsonString = Source.fromURL(github_doc_contents_url, "UTF-8").mkString;
    (Json.parse(jsonString) \ "size").validate[Int].asOpt.get
  }

  def judgeDoc(docId: Int): Boolean = {
    if (getDoumentSize(docId) == 0) false else true
  }
}
