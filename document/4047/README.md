###OneForOneStrategy策略
```scala
import akka.actor.SupervisorStrategy.Restart
import akka.actor.{Actor, ActorSystem, OneForOneStrategy, Props}
import akka.routing.RoundRobinPool

/**
 * Created by goku on 2015/9/9.
 */
object Test extends App {
  val system = ActorSystem("Test")
  val b = system.actorOf(Props[B], name = "B")
  b ! 1

  class B extends Actor {
     //这里定义失败策咯,子自己失败，重启自己
    override val supervisorStrategy = OneForOneStrategy() { case _: Exception =&gt; Restart }

     //然后在路由中，把这个失败策略放进去
    val a = context.actorOf(RoundRobinPool(5, supervisorStrategy = supervisorStrategy).props(Props[A]), name = "A")
// val a = context.actorOf(RoundRobinPool(5).props(Props[A]), name = "A")

    override def receive: Actor.Receive = {
      case 1 =&gt; 
        a ! 1
    }
  }

  class A extends Actor {

    override def preRestart(reason: Throwable, message: Option[Any]) {
      println("actor:" + self.path + ",preRestart child, reason:" + reason + ", message:" + message)
    }

    override def receive: Receive = {
      case a: Int =&gt;
        if (self.path.name == "$b")
          throw new Exception("test" + self.path.name)
    }
  }
}

```
