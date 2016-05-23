package models.gnote.dao

import java.sql.Date
import java.util.Date
import javax.inject.Inject

import models.gnote.dao.entity._
import play.api.db.slick.DatabaseConfigProvider
import slick.dbio.Effect.Write
import slick.driver.JdbcProfile
import slick.driver.MySQLDriver.api._
import slick.lifted.TableQuery
import slick.profile.FixedSqlAction

import scala.collection.mutable.ArrayBuffer
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
  def navigationTopOne = db.run(tableCategory.filter(_.father_id === 0).to[List].result)

  //目录列表
  def category(id: Int) = db.run(tableCategory.filter(_.father_id === id).to[List].result)

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

  def content(id: Int) = db.run(tableContent.filter(_.category_id === id).to[List].result)

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

  def addContent(id: Int)= {
    val seqId = Await.result(db.run(sql"select id from g_note.sequence".as[Int].head), Duration.Inf)
    Await.result(db.run(sqlu"update g_note.sequence set id = id + 1"), Duration.Inf)
    db.run(sqlu"""insert into content(id,content_1,content_2,category_id,createdata,updatedata) values($seqId,"???","???",$id,${new java.sql.Date(new java.util.Date().getTime)},${new java.sql.Date(new java.util.Date().getTime)})""")
  };

  def addDirWithContent(id: Int, dirName: String) = db.run(tableContent.map(c => (c.content_1, c.content_2, c.category_id, c.createdata, c.updatedata)) +=(dirName, "???", id, new java.sql.Date(new java.util.Date().getTime), new java.sql.Date(new java.util.Date().getTime)));

  def deleteContent(id: Int) = db.run(tableContent.filter(_.id === id).delete)

  def updateContent(id: Int, con_1: String, con_2: String, document_id: Option[Int], file_id: Option[Int]) = db.run(tableContent.filter(_.id === id).map(c => (c.content_1, c.content_2, c.document_id, c.file_id, c.updatedata)).update(con_1, con_2, document_id, file_id, new java.sql.Date(new java.util.Date().getTime)))

  def updateDir(id: Int, name: String) = db.run(tableCategory.filter(_.id === id).map(c => (c.name, c.updatedate)).update(name, new java.sql.Date(new java.util.Date().getTime)))

  //用于左侧目录索引显示，子目录数量和子目录内容数量
  def getDirsInfoAndCnt(id: Int) = {
    val a = tableCategory.filter(_.father_id === id)

    val b = tableCategory.filter(_.father_id in (a.map(_.id)))
      .groupBy(_.father_id).map { case (fatid, group) => (fatid, group.length) }

    val e = tableContent.filter(_.category_id in a.map(_.id))
      .groupBy(_.category_id).map { case (cid, group) => (cid, group.length) }

    val f = for {
      ((c, s), x) <- a joinLeft b on (_.id === _._1) joinLeft e on (_._1.id === _._1)
    } yield (c, s.map(_._2).ifNull(0), x.map(_._2).ifNull(0))

    db.run(f.to[List].result)
  }

}