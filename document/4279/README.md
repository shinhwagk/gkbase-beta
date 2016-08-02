```scala
object Test extends App {

  implicit val system = ActorSystem()
  implicit val materializer = ActorMaterializer()
  implicit val executionContext = system.dispatcher

  Source(1 to 19)
    .via(Flow[Int].map(p=>{println(1,p);p + 1}).async)
    .via(Flow[Int].map(p=>{println(2,p);p + 1}))
    .runForeach(p=>{
      Thread.sleep(10000)
      println(3,p)
    })
}
```
> 执行顺序还是1到19 但是是并行的,与mapAsync是不同的
