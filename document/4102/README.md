```java
public class Test {
    public static void main(String[] args) {
        G.gFun1((String x) -> x.length());
    }
}

interface I {
    int fun1(String s);
//    String fun2(int s); 如果你取消注释就会发现main方法中提示错误
}

class G {
    static void gFun1(I f1) {System.out.println(f1.fun1("fff"));}

    static void gFun2(I f2) {System.out.println(f2.fun1("fff"));}
}
```
>上述代码在不取消注释的情况下，可以正常运行
