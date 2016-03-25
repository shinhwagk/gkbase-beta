package models.gnote.dao.entity

import java.sql.Date

import slick.driver.MySQLDriver.api._

/**
  * Created by zhangxu on 16/3/14.
  */
case class Document(id: Int, state: Int, dtype: String, createdata: Date, updatedata: Date)

class Documents(tag: Tag) extends Table[Document](tag, "document") {
  def * = (id, state, dtype, createdata, updatedata) <>(Document.tupled, Document.unapply)

  def id = column[Int]("id", O.PrimaryKey, O.AutoInc)

  def state = column[Int]("state")

  def dtype = column[String]("dtype")

  def createdata = column[Date]("createdata")

  def updatedata = column[Date]("updatedata")
}