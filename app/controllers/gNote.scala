package controllers

import javax.inject.Inject

import models.gnote.config.gConfig
import models.gnote.dao.{DaoGNote, HtmlDataGNote}
import play.api.mvc._

import scala.concurrent.ExecutionContext
import scala.util.{Failure, Success}

/**
  * Created by zhangxu on 2016/3/16.
  */
class gNote @Inject()(data: HtmlDataGNote, daoGnote: DaoGNote)(implicit ec: ExecutionContext) extends Controller {

  def c(id: Int) = Action.async { implicit request =>
    data.getViewsDataGNote(id).map { d => Ok(views.html.note.index(d)) }
  }

  def d(id: Int) = Action { implicit request =>
    val path = gConfig.DOCUMENT_PATH
//    val o = scala.io.Source.fromFile(s"$path\\${id}.md")
//    Ok(views.html.note.document(o.mkString))
    Ok(views.html.note.document("#dfdf"))
  }

  def add_content(id: Int) = Action.async { implicit request =>
    daoGnote.addContent(id).map { p =>
      Redirect(routes.gNote.c(id))
    }
  }

  def add_directory(id: Int) = Action.async { implicit request =>
    daoGnote.addDir(id).map { p =>
      Redirect(routes.gNote.c(id))
    }
  }

  def delete_directory(id: Int) = Action.async {
    for {
      i <- daoGnote.getDir(id)
      j <- daoGnote.deleteDir(id)
    } yield Redirect(routes.gNote.c(i.head.father_id.getOrElse(0)))
  }

  def get_content(id: Int) = Action.async {
    daoGnote.getContent(id).map { p =>
      Ok(views.html.note.edit.content.update_modal(p.head))
    }
  }

  def get_category(id: Int) = Action.async {
    daoGnote.getCategory(id).map { p =>
      Ok(views.html.note.edit.directory.update_modal(p.head))
    }
  }

  def delete_content(id: Int) = Action.async { implicit request =>
    for {
      j <- daoGnote.getContent(id)
      i <- daoGnote.deleteContent(id)
    } yield Redirect(routes.gNote.c(j.head.category_id))
  }

  def update_content = Action.async { implicit request =>
    val pars = request.body.asFormUrlEncoded.get
    println(pars)
    val id = pars("content-update-id-val").head.toInt
    val did = pars("content-update-did-val").head.toInt
    val content_1 = pars("content-update-content-1-val").head
    val content_2 = pars("content-update-content-2-val").head
    val document_id = pars("content-update-docid-val").head.toInt
    val fexec = daoGnote.updateContent(id, content_1, content_2, document_id)
    fexec.map { p =>
      Ok(content_1)
    }
  }

  def update_dir = Action.async { implicit request =>
    val pars = request.body.asFormUrlEncoded.get
    val id = pars("dir-update-id-val").head.toInt
    val name = pars("dir-update-name-val").head
    daoGnote.updateDir(id, name).map { p =>
      Ok(name)
    }
  }
}