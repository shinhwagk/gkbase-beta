package models.gnote.dao

import models.gnote.dao.entity._
import play.api.Play
import play.api.db.slick.DatabaseConfigProvider
import slick.driver.JdbcProfile
import slick.driver.MySQLDriver.api._
import slick.lifted.TableQuery

import scala.collection.mutable.ArrayBuffer
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.duration.Duration
import scala.concurrent.{Await, Future}

/**
  * Created by zhangxu on 16/6/18.
  */
object ViewDao {
  val dbConfig = DatabaseConfigProvider.get[JdbcProfile](Play.current)
  val db = dbConfig.db
  val tableCategory = TableQuery[Categorys]
  val tableContent = TableQuery[Contents]

  def categoryTree(id: Int): Future[List[Category]] = {
    val bre = new ArrayBuffer[Category]()
    def tree(did: Int): Future[Int] = {
      for {
        row <- getDir(did)
        id <- {
          if (row.head.father_id.getOrElse(0) != 0) {
            bre += row.head
            tree(row.head.father_id.get)
          } else {
            bre += row.head
            Future.successful(0)
          }
        }
      } yield id
    }
    tree(id).map { b => bre.reverse.toList }
  }

  def getCategoryTree(id: Int) = Await.result(categoryTree(id), Duration.Inf)

  //用于左侧目录索引显示，子目录数量和子目录内容数量
  def dirsInfoAndCnt(id: Int): Future[List[(Category, Int, Int)]] = {
    val a = tableCategory.filter(p => p.father_id === id && p.state =!= 0)

    val b = tableCategory.filter(_.father_id in (a.map(_.id)))
      .groupBy(_.father_id).map { case (fatid, group) => (fatid, group.length) }

    val e = tableContent.filter(_.category_id in a.map(_.id))
      .groupBy(_.category_id).map { case (cid, group) => (cid, group.length) }

    val f = for {
      ((c, s), x) <- a joinLeft b on (_.id === _._1) joinLeft e on (_._1.id === _._1)
    } yield (c, s.map(_._2).ifNull(0), x.map(_._2).ifNull(0))

    db.run(f.to[List].result)
  }

  def getDirsInfoAndCnt(id: Int): List[(Category, Int, Int)] = Await.result(dirsInfoAndCnt(id), Duration.Inf)

  def getContent(did: Int): List[Content] = Await.result(db.run(tableContent.filter(p => p.category_id === did && p.state === 1).to[List].result), Duration.Inf)

  def getContentOne(id: Int) = Await.result(db.run(tableContent.filter(_.id === id).to[List].result), Duration.Inf)

  def navigationTopOne = db.run(tableCategory.filter(_.father_id === 0).to[List].result)

  def getNavigationTopOne = Await.result(navigationTopOne, Duration.Inf)

  //目录列表
  def category(id: Int) = db.run(tableCategory.filter(_.father_id === id).to[List].result)

  def getDir(id: Int) = db.run(tableCategory.filter(p => p.id === id && p.state =!= 0).to[List].result)

  def addDir(id2: Int) = {
    val seqId = Await.result(db.run(sql"select id from g_note.sequence".as[Int].head), Duration.Inf)
    Await.result(db.run(sqlu"update g_note.sequence set id = id + 1"), Duration.Inf)
    Await.result(db.run(sqlu"""insert into category values($seqId,"",$id2,${new java.sql.Date(new java.util.Date().getTime)},${new java.sql.Date(new java.util.Date().getTime)},1)"""), Duration.Inf)
    Future(seqId)
  }

  def deleteDir(id: Int) = db.run(tableCategory.filter(_.id === id).map(_.state).update(0))

  def updateDir(id: Int, name: String) = db.run(tableCategory.filter(_.id === id).map(c => (c.name, c.updatedate)).update(name, new java.sql.Date(new java.util.Date().getTime)))

  def getContentCount(did: Int) = db.run(tableContent.filter(_.category_id === did).length.result)

  def getCategoryCount(fid: Int) = db.run(tableCategory.filter(p => p.father_id === fid && p.state === 1).length.result)

  def getCategory(id: Int) = db.run(tableCategory.filter(_.father_id === id).to[List].result)

  def addContent(id: Int) = {
    val seqId = Await.result(db.run(sql"select id from g_note.sequence".as[Int].head), Duration.Inf)
    Await.result(db.run(sqlu"update g_note.sequence set id = id + 1"), Duration.Inf)
    db.run(sqlu"""insert into content(id,content_1,content_2,category_id,createdata,updatedata) values($seqId,"???","???",$id,${new java.sql.Date(new java.util.Date().getTime)},${new java.sql.Date(new java.util.Date().getTime)})""")
  };

  def addDirWithContent(id: Int, dirName: String) = db.run(tableContent.map(c => (c.content_1, c.content_2, c.category_id, c.createdata, c.updatedata)) +=(dirName, "???", id, new java.sql.Date(new java.util.Date().getTime), new java.sql.Date(new java.util.Date().getTime)));

  def deleteContent(id: Int) = db.run(tableContent.filter(_.id === id).map(_.state).update(0))

  def updateContent(id: Int, did: Int, con_1: String, con_2: String, document_id: Int, file_id: Int, source: String) = db.run(tableContent.filter(_.id === id).map(c => (c.category_id, c.content_1, c.content_2, c.document_id, c.file_id, c.updatedata, c.source)).update(did, con_1, con_2, document_id, file_id, new java.sql.Date(new java.util.Date().getTime), source))
}
