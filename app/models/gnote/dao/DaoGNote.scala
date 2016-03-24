package models.gnote.dao

import java.util.Date
import javax.inject.Inject

import models.gnote.dao.entity._
import play.api.db.slick.DatabaseConfigProvider
import slick.driver.JdbcProfile
import slick.driver.MySQLDriver.api._
import slick.lifted.TableQuery

import scala.collection.mutable.ArrayBuffer
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

/**
  * Created by zhangxu on 16/3/16.
  */
class DaoGNote @Inject()(dbConfigProvider: DatabaseConfigProvider) {
  val dbConfig = dbConfigProvider.get[JdbcProfile]
  val db = dbConfig.db
  val tableCategory = TableQuery[Categorys]
  val tableContent = TableQuery[Contents]

  //导航顶部数据
  def navigationTopOne = db.run(tableCategory.filter(_.father_id === null.asInstanceOf[Option[Int]]).to[List].result)

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
    tree(id).map { b => println(bre); bre.reverse.toList }
  }

  def content(id: Int) = db.run(tableContent.filter(_.category_id === id).to[List].result)

  def addDir(id: Int) = db.run(DBIO.seq(tableCategory.map(c => (c.name, c.father_id, c.createdata, c.updatedata)) +=("???", id, new java.sql.Date(new Date().getTime), new java.sql.Date(new Date().getTime))));

  def deleteDir(id: Int) = db.run(tableCategory.filter(_.id === id).delete)

  def getDir(id: Int) = db.run(tableCategory.filter(_.id === id).to[List].result)

  def getContent(id: Int) = db.run(tableContent.filter(_.id === id).to[List].result)

  def getContentCount(did: Int) = db.run(tableContent.filter(_.category_id === did).length.result)

  def getCategoryCount(fid: Int) = db.run(tableCategory.filter(_.father_id === fid).length.result)

  def getCategory(id: Int) = db.run(tableCategory.filter(_.father_id === id).to[List].result)

  def addContent(id: Int) = db.run(DBIO.seq(tableContent.map(c => (c.content_1, c.content_2, c.category_id, c.createdata, c.updatedata)) +=("???", "???", id, new java.sql.Date(new Date().getTime), new java.sql.Date(new Date().getTime))));

  def deleteContent(id: Int) = db.run(tableContent.filter(_.id === id).delete)

  def updateContent(id: Int, con_1: String, con_2: String) = db.run(tableContent.filter(_.id === id).map(c => (c.content_1, c.content_2, c.updatedata)).update(con_1, con_2, new java.sql.Date(new Date().getTime)))

  def updateDir(id: Int, name: String) = db.run(tableCategory.filter(_.id === id).map(c => (c.name, c.updatedata)).update(name, new java.sql.Date(new Date().getTime)))

  def test(id: Int) = {
    val abc3 = sql"""
  select category_id ,count(*)
  from content p
  where p.category_id in (select id from category where father_id = $id) group by category_id
  union
                  select id,0
                                                    from category
                                                    where father_id = $id

""".as[(Int, Int)]


    val abc = sql"""
  select father_id,count(*)
  from category p
  where p.father_id in (select id
                 from category
                 where father_id = $id) group by father_id
                 UNION
                 select id,0
                                  from category
                                  where father_id = $id

""".as[(Int, Int)]
    val a = tableCategory.filter(_.father_id === id)

    val b = tableCategory.filter(_.father_id in (a.map(_.id)))
      .groupBy(_.father_id).map { case (fatid, group) => (fatid, group.length) }
    val c = b.union(a.map { p => (p.id, 0) })
    val d = c.groupBy(_._1).map { p => (p._1, p._2.map(_._2).sum) }

    val e = tableContent.filter(_.category_id in a.map(_.id))
      .groupBy(_.category_id).map { case (cid, group) => (cid, group.length) }
    val f = e.union(a.map { p => (p.id, 0) })
    val g = f.groupBy(_._1).map { p => (p._1, p._2.map(_._2).sum) }

    //    val h = (d join g on (_._1 === _._1)).map( case (dv, gv) => (dv.))
    val h = for (p <- d;
                 a <- g if p._1 === a._1
    ) yield (p._1, p._2.getOrElse(0), a._2)

    val j = for (
      z <- h;
      y <- a if z._1 === y.id
    )yield(z._1,y.name,z._2,z._3.getOrElse(0))
    //    val abc = for {
    //      a <- catinfo
    //      b <- catcount if a.id === b._1
    //      c <- concount if a.id === c._1
    //    } yield (a.id, b._2, c._2)
    //    println(abc.head.statements)
      db.run(h.to[List].result)
    //    db.run(catcount.map { p => (p._1, p._2) }.to[List].result)
  }

}