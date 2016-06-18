package models.gnote.dao

import javax.inject.Inject

import models.gnote.dao.entity._
import play.api.db.slick.DatabaseConfigProvider
import slick.driver.JdbcProfile
import slick.driver.MySQLDriver.api._
import slick.lifted.TableQuery

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration.Duration
import scala.concurrent.{Await, Future}

/**
  * Created by zhangxu on 16/3/16.
  */
class DaoGNote @Inject()(dbConfigProvider: DatabaseConfigProvider) {
  val dbConfig = dbConfigProvider.get[JdbcProfile]
  val db = dbConfig.db
  val tableCategory = TableQuery[Categorys]
  val tableContent = TableQuery[Contents]

  //导航顶部数据
  //  def navigationTopOne = db.run(tableCategory.filter(_.father_id === null.asInstanceOf[Option[Int]]).to[List].result)
  //  def navigationTopOne = db.run(tableCategory.filter(_.father_id === 0).to[List].result)

  //目录列表
  def category(id: Int) = db.run(tableCategory.filter(_.father_id === id).to[List].result)

  def addDir(id2: Int) = {
    val seqId = Await.result(db.run(sql"select id from g_note.sequence".as[Int].head), Duration.Inf)
    Await.result(db.run(sqlu"update g_note.sequence set id = id + 1"), Duration.Inf)
    Await.result(db.run(sqlu"""insert into category values($seqId,"",$id2,${new java.sql.Date(new java.util.Date().getTime)},${new java.sql.Date(new java.util.Date().getTime)})"""), Duration.Inf)
    Future(seqId)
  }

  def deleteDir(id: Int) = db.run(tableCategory.filter(_.id === id).delete)

  def getDir(id: Int) = db.run(tableCategory.filter(_.id === id).to[List].result)

  def getContent(id: Int) = db.run(tableContent.filter(_.id === id).to[List].result)

  def getContentCount(did: Int) = db.run(tableContent.filter(_.category_id === did).length.result)

  def getCategoryCount(fid: Int) = db.run(tableCategory.filter(_.father_id === fid).length.result)

  def getCategory(id: Int) = db.run(tableCategory.filter(_.father_id === id).to[List].result)

  def addContent(id: Int) = {
    val seqId = Await.result(db.run(sql"select id from g_note.sequence".as[Int].head), Duration.Inf)
    Await.result(db.run(sqlu"update g_note.sequence set id = id + 1"), Duration.Inf)
    db.run(sqlu"""insert into content(id,content_1,content_2,category_id,createdata,updatedata) values($seqId,"???","???",$id,${new java.sql.Date(new java.util.Date().getTime)},${new java.sql.Date(new java.util.Date().getTime)})""")
  };

  def addDirWithContent(id: Int, dirName: String) = db.run(tableContent.map(c => (c.content_1, c.content_2, c.category_id, c.createdata, c.updatedata)) +=(dirName, "???", id, new java.sql.Date(new java.util.Date().getTime), new java.sql.Date(new java.util.Date().getTime)));

  def deleteContent(id: Int) = db.run(tableContent.filter(_.id === id).delete)

  def updateContent(id: Int, con_1: String, con_2: String, document_id: Option[Int], file_id: Option[Int]) = db.run(tableContent.filter(_.id === id).map(c => (c.content_1, c.content_2, c.document_id, c.file_id, c.updatedata)).update(con_1, con_2, document_id, file_id, new java.sql.Date(new java.util.Date().getTime)))

  def updateDir(id: Int, name: String) = db.run(tableCategory.filter(_.id === id).map(c => (c.name, c.updatedate)).update(name, new java.sql.Date(new java.util.Date().getTime)))

}