package controllers.gnote

import play.api.mvc.Security.AuthenticatedBuilder
import play.api.mvc._

import scala.concurrent.Future

/**
  * Created by zhangxu on 2016/3/29.
  */
object Action {

  object LoggingAction extends ActionBuilder[Request] with Results {
    def invokeBlock[A](request: Request[A], block: (Request[A]) => Future[Result]) = {
      request.session.get("username").map { p =>
        block(request)
      }.getOrElse {
        //        Future.successful(Ok("a"))
        Future.successful(Forbidden("Only HTTPS requests allowed"))
      }
    }
  }

}

