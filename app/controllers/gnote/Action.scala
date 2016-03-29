package controllers.gnote

import play.api.mvc.Security.AuthenticatedBuilder
import play.api.mvc._

import scala.concurrent.Future

/**
  * Created by zhangxu on 2016/3/29.
  */
object Action {
  object LoggingAction extends ActionBuilder[Request] {
    def invokeBlock[A](request: Request[A], block: (Request[A]) => Future[Result]) = {
        block(request)
    }
  }
 
}
