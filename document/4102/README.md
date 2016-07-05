```java
public class Test {
    public static void main(String[] args) {
        G.gFun1((String x) -> x.length());
    }
}

interface I {
    int fun1(String s);
//    String fun2(int s); 如果你取消注释就会发现main方法中提示错误
    default String fun3(String s){
        return s;
    }
}

class G {
    static void gFun1(I f1) {
        System.out.println(f1.fun1("fff"));
    }

    static void gFun2(I f2) {
        System.out.println(f2.fun1("fff"));
    }
}
```
>上述代码在不取消注释的情况下，可以正常运行，即使有java8的新特性在接口实现默认方法，只要在接口中只能有一个抽象方法，那么你就可以通过lambda表达式来创建接口的对象，这种接口被称为函数式接口
