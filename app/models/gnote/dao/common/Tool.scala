package models.gnote.dao.common

import java.time.{LocalDate, LocalTime}

/**
  * Created by zhangxu on 16/5/24.
  */
object Tool extends App {
  def getTime: Long = {
    val t = LocalTime.now()
    val d = LocalDate.now()

    implicit def intForMatToString(a: Int): String = a.formatted("%02d")

    val year: String = d.getYear
    val mon: String = d.getMonth.getValue
    val day: String = d.getDayOfMonth
    val hour: String = t.getHour
    val min: String = t.getMinute
    val sec: String = t.getSecond
    (year + mon + day + hour + min + sec).toLong
  }
}
