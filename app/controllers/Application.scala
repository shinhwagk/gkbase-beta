package controllers

import play.api.mvc._

/**
  * Created by zhangxu on 16/3/14.
  */
object Application extends Controller {
  def index = Action{
    return Ok(views.html.index.render)
  }
}