###对象::实例方法
```java

```


###类::实例方法
```java
interface FunItf {
    void fun2(A a, String s2);
}

public class Test3 {
    public static void main(String[] args) {
        Test3.testFun2(new A(), "f", (s, x) -> s.f(x));
        Test3.testFun2(new A(), "f", A::f);
    }
    static void testFun2(A s1, String s2, FunItf f) {
        f.fun2(s1, s2);
    }
}

class A {
    void f(String s) {
        System.out.println(s);
    }
}
```
