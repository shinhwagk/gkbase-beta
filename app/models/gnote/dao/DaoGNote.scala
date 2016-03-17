package models.gnote.dao

import java.util.Date
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

  //导航顶部数据(一级)
  def navigationTopOne = db.run(tableCategory.filter(_.father_id === null.asInstanceOf[Option[Int]]).to[List].result)
  //导航顶部数据(二级)
  def navigationTopTwo = db.run(tableCategory.filter(_.father_id === 1).to[List].result)
  //目录列表
  def category(id:Int) = db.run(tableCategory.filter(_.father_id === id).to[List].result)

  def content(id:Int) = db.run(tableContent.filter(_.category_id === id).to[List].result)

  def addDir(id:Int,name:String) = db.run(DBIO.seq(tableCategory.map(c=>(c.name,c.father_id,c.createdata,c.updatedata)) +=(name,id,new java.sql.Date(new Date().getTime),new java.sql.Date(new Date().getTime))));
}
