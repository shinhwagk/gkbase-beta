package models.gnote.dao

import java.sql.Connection
import javax.inject.Inject

import models.gnote.dao.entity.{Category, Content}

import scala.collection.mutable.ArrayBuffer
import scala.concurrent.ExecutionContext.Implicits.global

/**
  * Created by zhangxu on 2016/3/16.
  */
class HtmlDataGNote @Inject()(daoGNote: DaoGNote) {

  def getViewsDataGNote(id: Int) = for {
    navOne <- daoGNote.navigationTopOne
    dirs <- daoGNote.category(id)
    conts <- daoGNote.content(id)
  } yield ViewsDataGNote(
    id,
    HtmlDataNavigationTopOne(navOne),
    HtmlDataBreadcrumb(daoGNote.categoryTree(id).toList),
    HtmlDataDirectory(dirs),
    HtmlDataContent(conts))

}

case class ViewsDataGNote(id: Int, navOne: HtmlDataNavigationTopOne, brdTree: HtmlDataBreadcrumb, dirs: HtmlDataDirectory, conts: HtmlDataContent)

case class HtmlDataNavigationTopOne(navOne: List[Category])

case class HtmlDataBreadcrumb(brd: List[Category])

case class HtmlDataDirectory(dirs: List[Category])

case class HtmlDataContent(conts: List[Content])