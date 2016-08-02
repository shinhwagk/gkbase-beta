```scala
object Test extends App {
  def b1 = Random.nextInt(10)

  val f = Future(if (b1 > 5) 9 else throw new Exception("111"))
  val b = f recover { case e: Exception => 6 }
  b.map(_*10).foreach(println) // 90 or 6

  while (true) {
    Thread.sleep(1000)
  }
}
```
