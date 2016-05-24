package models.gnote.dao

import javax.inject.Inject

import play.api.db.slick.DatabaseConfigProvider
import slick.driver.JdbcProfile
import slick.driver.MySQLDriver.api._

import scala.concurrent.duration.Duration
import scala.concurrent.{Await, Future}

/**
  * Created by zhangxu on 16/5/23.
  */
class JsonDao @Inject()(dbConfigProvider: DatabaseConfigProvider) {
  val db = dbConfigProvider.get[JdbcProfile].db

  def getSequence = {
    Await.result(db.run(sqlu"update sequence set id = id + 1"), Duration.Inf)
    Await.result(db.run(sql"select id from sequence".as[Int].head), Duration.Inf)
  }

  def folderAdd(name: String) = {
    val id = getSequence
    val value = s"""json_object("id",${id},"name","${name}","state",true,"createdate",111,"updatedate",222,"colsedate",33)"""
    db.run(sqlu"""update folders set data = json_array_append(data,'$$.folder',${value})""")
  }

  //  def folderUpdate
  //
  //  =???
  //
  //  def folderDelete
  //
  //  =???

  def contentAdd(content_01: String, content_02: String) = {
    val id = getSequence
    val value = s"""json_object("id",${id},"content_01","${content_01}","content_02",${content_02},"state",true,"createdate",111,"updatedate",222,"colsedate",33)"""
    db.run(sqlu"""update folders set data = json_array_append(data,'$$.records',${value})""")
  }


  //  def contentUpdate
  //
  //  =???
  //
  //  def contentDetele
  //
  //  =???

  def getFolders(id: Int) = {
    db.run(sql"""select data from folders where id = $id""".as[String])
  }
}
