package models.gnote.dao.table

import models.gnote.dao.entity.Categorys
import slick.lifted.TableQuery

/**
  * Created by zhangxu on 16/3/14.
  */
object Category {
  val tableCategory = TableQuery[Categorys]
}
