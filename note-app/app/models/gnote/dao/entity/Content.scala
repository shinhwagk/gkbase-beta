package models.gnote.dao.entity

import java.sql.Date
import slick.driver.MySQLDriver.api._

/**
  * Created by zhangxu on 16/3/14.
  */
case class Content(id: Int, content_1: String, content_2: String, category_id: Int, document_id: Int, file_id: Int, createdata: Date, updatedata: Date, state: Int, source: String)

class Contents(tag: Tag) extends Table[Content](tag, "content") {
  def * = (id, content_1, content_2, category_id, document_id, file_id, createdata, updatedata, state, source) <>(Content.tupled, Content.unapply)

  def id = column[Int]("id", O.PrimaryKey, O.AutoInc)

  def content_1 = column[String]("content_1")

  def content_2 = column[String]("content_2")

  def category_id = column[Int]("category_id")

  def document_id = column[Int]("document_id")

  def file_id = column[Int]("file_id")

  def createdata = column[Date]("createdata")

  def updatedata = column[Date]("updatedata")

  def state = column[Int]("state")

  def source = column[String]("source")
}