package controllers

import javax.inject.Inject

import play.api.db.slick.DatabaseConfigProvider
import play.api.mvc._
import slick.driver.JdbcProfile
import slick.driver.MySQLDriver.api._

/**
  * Created by zhangxu on 16/3/14.
  */
class GNote @Inject()(dbConfigProvider: DatabaseConfigProvider) extends Controller {
  val dbConfig = dbConfigProvider.get[JdbcProfile]

  def index(id: Int) = Action {
    import models.gnote.dao.table.Category._
    val topNav = tableCategory.filter(_.father_id === 0)

    val list = tableCategory.filter(_.father_id === id)
    //    val Content_list: util.List[Content] = b.findAll.stream.filter(p -> p.getCategory_id() == id).collect(Collectors.toList)

    Ok(views.html.index())
  }
}
