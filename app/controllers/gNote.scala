package controllers

import javax.inject.Inject

import models.gnote.dao.{DaoGNote, HtmlDataGNote}
import play.api.data.Forms._
import play.api.data._
import play.api.data.format.Formats._
import play.api.mvc._

import scala.concurrent.ExecutionContext

/**
  * Created by zhangxu on 2016/3/16.
  */
class gNote @Inject()(data: HtmlDataGNote, daoGnote: DaoGNote)(implicit ec: ExecutionContext) extends Controller {
  //d
  def c(id: Int) = Action.async { implicit request =>
    data.getViewsDataGNote(id).map { d => Ok(views.html.note.index(d)) }
  }
  //  def dirAdd(id: Int, name: String) = Action {
  def dirAdd = Action.async { implicit request =>
    val data2 = request.body.asFormUrlEncoded.get
    val id = data2("dir-add-id-val").head.toInt
    val name = data2("dir-add-name-val").head
    daoGnote.addDir(id,name).map {
      p => Ok("aaaa")
    }
  }
}
