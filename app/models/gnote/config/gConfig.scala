package models.gnote.config

import com.typesafe.config.ConfigFactory

/**
  * Created by zhangxu on 2016/3/24.
  */
object gConfig {
  val DOCUMENT_PATH = play.Configuration.root().getString("note.doc.path")
}
