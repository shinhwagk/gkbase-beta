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

  def getDir(id: Int) = db.run(tableCategory.filter(_.id === id).to[List].result)

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

  def getDirsInfoAndCnt(id: Int) = Await.result(dirsInfoAndCnt(id), Duration.Inf)

  def content(id: Int) = db.run(tableContent.filter(_.category_id === id).to[List].result)

  def getContent(id: Int) = Await.result(content(id), Duration.Inf)

  def navigationTopOne = db.run(tableCategory.filter(_.father_id === 0).to[List].result)

  def getNavigationTopOne = Await.result(navigationTopOne, Duration.Inf)

}
