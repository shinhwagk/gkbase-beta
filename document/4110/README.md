```java
public class Test5 {
    public static void main(String[] args) {
        List<Integer> list = new ArrayList(){};
        list.add(1);
        list.add(2);
        list.stream().map(int[]::new);
    }
}
```
