```java
class Greeter {
    public void greet() {
        System.out.println("a");
    }
}

public class Test5 extends Greeter {
    void greet() {
        Thread t = new Thread(super::greet);
        t.start();
    }
}
```
