###lambda表达式分三个部分
- 一段代码 
- 参数 
- 自由变量的值，指的是没有在代码中定义的变量

```java
static void fun(String count, String text){
  Runcable r = ()->{
    for (int i = 0; i < count; i++){
      System.out.println(text);
    }
  }
  new Thread(r).start();
}
```

#### 一段代码
```java
  for (int i = 0; i < count; i++){
    System.out.println(text);
  }
```
#### 参数
```java
()//这里没有
```
#### 自由变量
>count text就是自由变量

* 如果把lambda表达式转换成为一个只含有一个方法的对象，那么自由变量的值就会被复制到该对象的实例变量中。
* 一个重要的约束，就是自由变量不能修改，例如，你在代码块中执行count += 1
