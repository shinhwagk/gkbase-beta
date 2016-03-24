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
    t <- daoGNote.test(id)
  } yield ViewsDataGNote(
    id,
    HtmlDataNavigationTopOne(navOne),
    HtmlDataBreadcrumb(brd),
    HtmlDataDirectory(dirs),
    HtmlDataContent(conts),
    test(t.map{p=>(p._1,p._2.getOrElse(0))})
  )
}

case class ViewsDataGNote(id: Int, navOne: HtmlDataNavigationTopOne, brdTree: HtmlDataBreadcrumb, dirs: HtmlDataDirectory, conts: HtmlDataContent, test: test)

case class CategoryCountVersion(category: Category, dirCnt: Int, cetCnt: Int)

case class HtmlDataNavigationTopOne(navOne: List[Category])

case class HtmlDataBreadcrumb(brd: List[Category])

case class HtmlDataDirectory(dirs: List[Category])

case class HtmlDataContent(conts: List[Content])

case class test(ddd: List[( Int, Int)])