package controllers

import javax.inject.Inject

import models.gnote.dao.{ViewDao}
import play.api.mvc._

import scala.concurrent.ExecutionContext

/**
  * Created by zhangxu on 2016/3/16.
  */
class gNote @Inject()(implicit ec: ExecutionContext) extends Controller {

  implicit val myCustomCharset = Codec.utf_8

  def c(id: Int) = Action {
    Ok(views.html.note.index(id))
  }

  def d(id: Int) = Action { implicit request =>
    val path = play.Configuration.root().getString("note.doc.path")
    val github_doc_url = s"$path/${id}/README.md"
    Redirect(github_doc_url)
  }

  def f(id: Int) = Action { implicit request =>
    val path = play.Configuration.root().getString("note.file.path")
    val github_file_url = s"$path/$id"
    Redirect(github_file_url)
  }

  def add_content(id: Int) = Action.async { implicit request =>
    ViewDao.addContent(id).map { p =>
      Redirect(routes.gNote.c(id))
    }
  }

  def add_directory(id: Int) = Action.async { implicit request =>
    for {
      newDirId <- ViewDao.addDir(id)
      dirInfo <- ViewDao.getDir(newDirId)
    } yield Ok(views.html.note.edit.directory.update_modal(dirInfo.head))
  }

  def delete_directory(id: Int) = Action.async { implicit request =>
    for {
      i <- ViewDao.getDir(id)
      j <- ViewDao.deleteDir(id)
    } yield Ok(i.head.father_id.getOrElse(0).toString)
  }

  def get_content(id: Int) = Action {
    Ok(views.html.note.edit.content.update_modal(ViewDao.getContentOne(id).head))
  }

  def get_category(id: Int) = Action.async {
    ViewDao.getDir(id).map { p =>
      Ok(views.html.note.edit.directory.update_modal(p.head))
    }
  }

  def delete_content(id: Int) = Action.async { implicit request =>
    for {
      i <- ViewDao.deleteContent(id)
    } yield Ok(ViewDao.getContentOne(id).head.category_id.toString)
  }

  def update_content = Action.async { implicit request =>
    val pars = request.body.asFormUrlEncoded.get
    val id = pars("content-update-id-val").head.toInt
    val did = pars("content-update-did-val").head.toInt
    val content_1 = pars("content-update-content-1-val").head
    val content_2 = pars("content-update-content-2-val").head
    val document_id_par: Seq[String] = pars("content-update-docid-val")
    println("a" + document_id_par + "b" + document_id_par.size + "cc")
    val file_id_par = pars("content-update-fileid-val").head
    val source = pars("content-update-source-val").head
    val document_id = if (document_id_par == "") None else Some(document_id_par)
    val file_id = if (file_id_par == "") None else Some(file_id_par.toInt)
    ViewDao.updateContent(id, did, content_1, content_2, document_id, file_id, source).map { p =>
      Ok(content_1)
    }
  }

  def update_dir = Action.async { implicit request =>
    val pars = request.body.asFormUrlEncoded.get
    val id = pars("dir-update-id-val").head.toInt
    val name = pars("dir-update-name-val").head
    ViewDao.updateDir(id, name).map { p =>
      Ok(name)
    }
  }
}
