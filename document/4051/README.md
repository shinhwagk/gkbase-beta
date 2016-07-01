```scala

import akka.actor.{Props, ActorSystem, Actor}
import akka.actor.Actor.Receive
import com.typesafe.config.ConfigFactory
import org.gk.workers.HeadParser

/**
 * Created by gk on 15/7/26.
 */
object test6 {
  def main(args: Array[String]) {
    val system = ActorSystem("MavenProxy111")
    val aa = system.actorOf(Props[aa],"aa")
    aa ! "a"
  }

}

class aa extends Actor with akka.actor.ActorLogging{
  val a = 1
  val bb = context.actorOf(Props[bb],"bb")
  override def receive: Receive = {
    case "a" =&gt;
      bb forward "a"
    case "b" =&gt;
      println("aaaaa")
 //class cc的sender 被这里收到
  }
}

class bb extends Actor with akka.actor.ActorLogging{
  val a = 1
  val cc = context.actorOf(Props[cc],"cc")
  override def receive: Receive = {
    case "a" =&gt;
      println(sender().path.name)
      cc forward "a"
    case "b" =&gt;
      println("bbbbb")

  }
}

class cc extends Actor with akka.actor.ActorLogging{
  val a = 1
  override def receive: Receive = {
    case "a" =&gt;
      println(sender().path.name)
 //aa
      sender() ! "b"
 
  }
}

```
