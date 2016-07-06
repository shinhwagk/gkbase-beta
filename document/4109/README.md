```java
public class Test5 {
    public static void main(String[] args) {
        List<String> list = new ArrayList(){};
        list.add("a");
        list.add("b");
        Stream<A> stream = list.stream().map(A::new);
        stream.collect(Collectors.toList()).forEach(x->System.out.println(x.s));
    }
}

class A {
    String s;
    public A(String s) {
        this.s = s;
    }
}
```
>直接用参数传递给class的构造器来创建对象
