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
