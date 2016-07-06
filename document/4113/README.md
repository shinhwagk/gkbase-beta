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
###如果把Named改成未实现默认方法,也需要手动指定，和上面一样的
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
     String getName();
}
```
