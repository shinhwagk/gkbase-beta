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
<<<<<<< Updated upstream
    HtmlDataDirectory(t.map { p => CategoryInfoCount(p._1, p._2, p._3) }),
=======
    HtmlDataDirectory(t.map{p=>CategorysAndCount(p._1,p._2,p._3)}),
>>>>>>> Stashed changes
    HtmlDataContent(conts)
  )
}

case class ViewsDataGNote(id: Int, navOne: HtmlDataNavigationTopOne, brdTree: HtmlDataBreadcrumb, dirs: HtmlDataDirectory, conts: HtmlDataContent)

<<<<<<< Updated upstream
case class CategoryInfoCount(category: Category, dirCnt: Int, cetCnt: Int)
=======
case class CategorysAndCount(category: Category, dirCnt: Int, cetCnt: Int)
>>>>>>> Stashed changes

case class HtmlDataNavigationTopOne(navOne: List[Category])

case class HtmlDataBreadcrumb(brd: List[Category])

<<<<<<< Updated upstream
case class HtmlDataDirectory(dirs: List[CategoryInfoCount])
=======
case class HtmlDataDirectory(dirs: List[CategorysAndCount])
>>>>>>> Stashed changes

case class HtmlDataContent(conts: List[Content])