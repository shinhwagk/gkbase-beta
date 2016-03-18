package models.gnote.dao

import java.sql.Connection
import javax.inject.Inject

import models.gnote.dao.entity.{Content, Category}
import scala.concurrent.ExecutionContext.Implicits.global

/**
  * Created by zhangxu on 2016/3/16.
  */
class HtmlDataGNote @Inject()(daoGNote: DaoGNote) {

  def getViewsDataGNote(id: Int) = for {
    navOne <- daoGNote.navigationTopOne
    navTwo <- daoGNote.navigationTopTwo
    dirs <- daoGNote.category(id)
    conts <- daoGNote.content(id)
  } yield ViewsDataGNote(HtmlDataNavigationTopOne(navOne), HtmlDataNavigationTopTwo(navTwo), HtmlDataDirectory(dirs), HtmlDataContent(conts))
}

case class ViewsDataGNote(navOne: HtmlDataNavigationTopOne, navTwo: HtmlDataNavigationTopTwo, dirs: HtmlDataDirectory, conts: HtmlDataContent)

case class HtmlDataNavigationTopOne(navOne:List[Category])
case class HtmlDataNavigationTopTwo(navTwo:List[Category])
case class HtmlDataDirectory(dirs:List[Category])
case class HtmlDataContent(conts:List[Content])