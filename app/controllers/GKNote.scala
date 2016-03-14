package controllers

import play.api.mvc._

/**
  * Created by zhangxu on 16/3/14.
  */
object GKNote extends Controller {
  def index(id: Int): Result = Action{
    val a: ImplCategory = new ImplCategory
    val b: ImplContent = new ImplContent
    val topNav: util.List[Category] = a.findAll.stream.filter(p -> p.getFather_id() == 0).collect(Collectors.toList)
    val list: util.List[Category] = a.findAll.stream.filter(p -> p.getFather_id() == id).collect(Collectors.toList)
    val Content_list: util.List[Content] = b.findAll.stream.filter(p -> p.getCategory_id() == id).collect(Collectors.toList)
    return Ok(views.html.note.index.render(topNav, list, Content_list))
  }
}
