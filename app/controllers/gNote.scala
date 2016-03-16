package controllers

import javax.inject.Inject

import models.gnote.dao.HtmlDataGNote
import play.api.mvc._

import scala.concurrent.ExecutionContext

/**
  * Created by zhangxu on 2016/3/16.
  */
class gNote @Inject()(data: HtmlDataGNote)(implicit ec: ExecutionContext) extends Controller {
  //d
  def c(id: Int) = Action.async {
    data.getViewsDataGNote(id).map { d => Ok(views.html.note.index(d)) }
  }
}
