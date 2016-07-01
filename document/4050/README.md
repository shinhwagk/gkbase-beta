###actor有4个生命创建和结束，以及执行出错时的相应的方法，以下4个：
```text
preStart -&gt; [preRestart] -&gt; [postRestart] -&gt; postStop
```
中间2个中括号只有在需要的时候才会执行(比如执行出错)，但是两端必须会执行

#####在创建actor的时候就会执行preStart
```scala
context.actorOf(Props[xxx])
```

#####在关闭actor的时候会执行postStop


#####在重启的时候会执行preRestart和postRestart，重启执行preRestart，执行完毕preRestart后，执行postRestart
