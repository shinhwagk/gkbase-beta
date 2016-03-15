package models.gnote.dao

import javax.inject.Inject

import models.gnote.dao.entity._
import play.api.db.slick.DatabaseConfigProvider
import slick.driver.JdbcProfile
import slick.driver.MySQLDriver.api._
import slick.lifted.TableQuery

/**
  * Created by zhangxu on 16/3/16.
  */
class DaoGNote @Inject()(dbConfigProvider: DatabaseConfigProvider) {
  val dbConfig = dbConfigProvider.get[JdbcProfile]
  val db = dbConfig.db

  val tableCategory = TableQuery[Categorys]
  val tableContent = TableQuery[Contents]

  def navigationTopOne = db.run(tableCategory.filter(_.father_id === null.asInstanceOf[Option[Int]]).to[List].result)
  def navigationTopTwo = db.run(tableCategory.filter(_.father_id === 1).to[List].result)

  def getcategory(id:Int) = db.run(tableCategory.filter(_.father_id === id).to[List].result)
}
