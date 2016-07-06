```java
public class Test6 extends X implements Person, Named {
    public static void main(String[] args) {
        System.out.println(new Test6().getName());
    }
}

interface Person {
    default String getName() {
        return "Person";
    }
}

interface Named {
    String getName();
}

class X {
    public String getName() {
        return "X";
    }
}
```
