```scala
import akka.http.scaladsl.model._
import scala.concurrent.Future
import akka.actor.ActorSystem
import akka.http.scaladsl.Http
import akka.stream.ActorMaterializer
import akka.stream.scaladsl._

/**
  * Created by zhangxu on 2016/7/29.
  */
object Bootstrap extends App {
  implicit val system = ActorSystem()
  implicit val materializer = ActorMaterializer()
  implicit val executionContext = system.dispatcher
  
  val serverSource: Source[Http.IncomingConnection, Future[Http.ServerBinding]] =
    Http().bind(interface = "localhost", port = 8080)

  val httpEcho = Flow[HttpRequest].map { request: HttpRequest =>
    HttpResponse(entity = HttpEntity(ContentTypes.`text/plain(UTF-8)`, s"<html><body>aa</body></html>"))
  }

  serverSource.to(Sink.foreach { conn: Http.IncomingConnection =>
    conn.handleWith(httpEcho)
  }).run()
}
```
>handleWith用于接收一个flow
