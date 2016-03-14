package models.gnote.dao.entity

import java.sql.Date
import slick.driver.MySQLDriver.api._

/**
  * Created by zhangxu on 16/3/14.
  */
case class Category(id: Option[Int], name: String, father_id: Int, createdate: Date, updatedata: Date)


class Categorys(tag: Tag) extends Table[Category](tag, "category") {
  def * = (id.?, name, father_id, createdata, updatedata) <>(Category.tupled, Category.unapply)

  def id = column[Int]("id", O.PrimaryKey, O.AutoInc)

  def name = column[String]("SUP_NAME")

  def father_id = column[String]("father_id")

  def createdata = column[String]("createdata")

  def updatedata = column[String]("updatedata")
}