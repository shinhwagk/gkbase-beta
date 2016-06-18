package controllers

import play.api.mvc._

/**
  * Created by zhangxu on 2016/3/28.
  */
class Application extends Controller {
  def index = Action { implicit request =>
    Ok(views.html.ii())
  }
}
