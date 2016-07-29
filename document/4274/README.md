```scala
import akka.http.scaladsl.model._
import scala.concurrent.Future

/**
  * Created by zhangxu on 2016/7/29.
  */
object Bootstrap extends App {

  import akka.actor.ActorSystem
  import akka.http.scaladsl.Http
  import akka.stream.ActorMaterializer
  import akka.stream.scaladsl._

  implicit val system = ActorSystem()
  implicit val materializer = ActorMaterializer()
  implicit val executionContext = system.dispatcher

  val serverSource: Source[Http.IncomingConnection, Future[Http.ServerBinding]] =
    Http().bind(interface = "localhost", port = 8080)

  val httpEcho = Flow[HttpRequest].map { request =>
    HttpResponse(entity = HttpEntity(ContentTypes.`text/plain(UTF-8)`, s"<html><body>aa</body></html>"))
  }

  serverSource.to(Sink.foreach { conn =>
    conn.handleWith(httpEcho)
  }).run()
}
```
