```scala
  Source(1 to 10).via(Flow[Int]
    .map { p => println(p, 1); Thread.sleep(1000); p + 1 })
    .buffer(5, OverflowStrategy.backpressure)
    .map { p => println(p, 3); Thread.sleep(1000); p + 1 }
    .buffer(2, OverflowStrategy.backpressure)
    .runWith(Sink.foreach { p => println(p, 2); Thread.sleep(100000) })
```
> 不管是map还是Flow都可以有自己的buffer，每个操作步骤称为stage
> 每个stage的buffer表示自己这部分可以存储elme的数量
