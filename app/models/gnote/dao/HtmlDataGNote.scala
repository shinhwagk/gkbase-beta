package models.gnote.dao

import javax.inject.Inject

import models.gnote.dao.entity.{Category, Content}

import scala.concurrent.ExecutionContext.Implicits.global

/**
  * Created by zhangxu on 2016/3/16.
  */
class HtmlDataGNote @Inject()(daoGNote: DaoGNote) {
  def getViewsDataGNote(id: Int) = for {
    navOne <- daoGNote.navigationTopOne
    brd <- daoGNote.categoryTree(id)
    conts <- daoGNote.content(id)
    dirs <- daoGNote.getCategory(id)
    c <- dirs.map{p=>daoGNote.getCategory(p.father_id.getOrElse(0))}
    b <- dirs.map{p=>daoGNote.getContent(p.father_id.getOrElse(0))}
    d <- c
    


  } yield ViewsDataGNote(
    id,
    HtmlDataNavigationTopOne(navOne),
    HtmlDataBreadcrumb(brd),
    HtmlDataDirectory(dirs),
    HtmlDataContent(conts))
}

case class ViewsDataGNote(id: Int, navOne: HtmlDataNavigationTopOne, brdTree: HtmlDataBreadcrumb, dirs: HtmlDataDirectory, conts: HtmlDataContent)

case class CategoryCountVersion(category: Category, dirCnt: Int, cetCnt: Int)

case class HtmlDataNavigationTopOne(navOne: List[Category])

case class HtmlDataBreadcrumb(brd: List[Category])

case class HtmlDataDirectory(dirs: List[Category])

case class HtmlDataContent(conts: List[Content])