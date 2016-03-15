package controllers

import javax.inject.Inject

import models.gnote.dao.DaoGNote
import play.api.db.slick.DatabaseConfigProvider
import play.api.libs.concurrent.Execution.Implicits.defaultContext
import play.api.mvc._

/**
  * Created by zhangxu on 16/3/14.
  */
class Application @Inject()(daoGNote: DaoGNote, dbConfigProvider: DatabaseConfigProvider) extends Controller {

  def index = Action.async {
    daoGNote.navigationTopOne.map { i => Ok(views.html.note.navigation(i)) }
  }

  //d
  def c(id: Int) = Action.async {
    daoGNote.getcategory(id).map { i => Ok(views.html.note.navigation(i)) }
  }


}
