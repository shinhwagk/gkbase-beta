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

###如果还有集成惨祸在里面会怎么样
```java
public class Test6 extends X implements Person, Named {
    public static void main(String[] args) {
        System.out.println(new Test6().getName());
    }

    @Override
    public String getName() {
        return X.class.getName();
        //return super.getName(); 这样也可以
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
    String getName() {
        return "X";
    }
}
```
