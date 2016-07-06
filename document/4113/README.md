```java
public class Test6 implements Person, Named {
    @Override
    public String getName() {
        return Person.super.getName();
    }
}


interface Person {
    default String getName() {
        return "Person";
    }
}

interface Named {
    default String getName() {
        return "Named";
    }
}
```
