```java
public class Test5 {
    public static void main(String[] args) {
        String[] ss = {"a", "f", "aa"};
        Arrays.asList(ss).stream().filter(p -> p.length() == 1).count();
    }
}
```
- stream创建stream
- filter操作，stream转到另一个stream
- count 立即执行
