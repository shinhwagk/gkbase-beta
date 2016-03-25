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
    t <- daoGNote.getDirsInfoAndCnt(id)
    conts <- daoGNote.content(id)
  } yield ViewsDataGNote(
    id,
    HtmlDataNavigationTopOne(navOne),
    HtmlDataBreadcrumb(brd),
    HtmlDataDirectory(t.map { p => CategoryInfoCount(p._1, p._2, p._3) }),
    HtmlDataContent(conts)
  )
}

case class ViewsDataGNote(id: Int, navOne: HtmlDataNavigationTopOne, brdTree: HtmlDataBreadcrumb, dirs: HtmlDataDirectory, conts: HtmlDataContent)

case class CategoryInfoCount(category: Category, dirCnt: Int, cetCnt: Int)

case class HtmlDataNavigationTopOne(navOne: List[Category])

case class HtmlDataBreadcrumb(brd: List[Category])

case class HtmlDataDirectory(dirs: List[CategoryInfoCount])

case class HtmlDataContent(conts: List[Content])