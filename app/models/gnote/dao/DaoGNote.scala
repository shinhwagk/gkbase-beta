package models.gnote.dao

import java.util.Date
import javax.inject.Inject

import models.gnote.dao.entity._
import play.api.db.slick.DatabaseConfigProvider
import slick.driver.JdbcProfile
import slick.driver.MySQLDriver.api._
import slick.lifted.TableQuery

import scala.concurrent.ExecutionContext.Implicits.global
import scala.collection.mutable.ArrayBuffer
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

  //导航顶部数据(一级)
  def navigationTopOne = db.run(tableCategory.filter(_.father_id === null.asInstanceOf[Option[Int]]).to[List].result)

  //目录列表
  def category(id: Int) = db.run(tableCategory.filter(_.father_id === id).to[List].result)

  def categoryTree(id: Int): ArrayBuffer[Category] = {
    val bre = new ArrayBuffer[Category]()
    def tree(did: Int): Unit = {
      val row = Await.result(getDir(did), Duration.Inf).head
      val fatId = row.father_id.getOrElse(0)
      bre += row
      if (fatId != 0) {
        tree(fatId)
      }
    }
    tree(id)
    bre.reverse
  }

  def content(id: Int) = db.run(tableContent.filter(_.category_id === id).to[List].result)

  def addDir(id: Int) = db.run(DBIO.seq(tableCategory.map(c => (c.name, c.father_id, c.createdata, c.updatedata)) +=("???", id, new java.sql.Date(new Date().getTime), new java.sql.Date(new Date().getTime))));

  def deleteDir(id: Int) = db.run(tableCategory.filter(_.id === id).delete)

  def getDir(id: Int) = db.run(tableCategory.filter(_.id === id).to[List].result)

  def getContent(id: Int) = db.run(tableContent.filter(_.id === id).to[List].result)

  def addContent(id: Int) = db.run(DBIO.seq(tableContent.map(c => (c.content_1, c.content_2, c.category_id, c.createdata, c.updatedata)) +=("???", "???", id, new java.sql.Date(new Date().getTime), new java.sql.Date(new Date().getTime))));

  def deleteContent(id: Int) = db.run(tableContent.filter(_.id === id).delete)

  def updateContent(id: Int, con_1: String, con_2: String) = db.run(tableContent.filter(_.id === id).map(c => (c.content_1, c.content_2, c.updatedata)).update(con_1, con_2, new java.sql.Date(new Date().getTime)))
}