package models.gnote.dao

import org.apache.http.client.methods.HttpGet
import org.apache.http.impl.client.HttpClients

/**
  * Created by zhangxu on 2016/7/6.
  */
object Test {
  val httpclient = HttpClients.createDefault();
  val path = play.Configuration.root().getString("note.doc.content")

  //  val path = "https://api.github.com/repos/shinhwagk/gkbase-beta/contents/document";
  def getDoumentSize(docId: Int): Int = {
    val github_doc_contents_url = s"$path/${docId}/README.md"
    val httpget = new HttpGet(github_doc_contents_url);
    val responce = httpclient.execute(httpget)
    if (responce.getStatusLine.getStatusCode == 404) 0 else 1
  }

  def judgeDoc(docId: Int): Boolean = {
    if (getDoumentSize(docId) == 0) false else true
  }

}
