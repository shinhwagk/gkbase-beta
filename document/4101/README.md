```java
public class Test {
    public static void main(String[] args) {
        String[] strings = {"c1zzzz", "as", "bcc", "cxxq"};
        Arrays.sort(strings, new LengthComparator());
        for (String i : strings) {
            System.out.println(i);
        }
    }
}

class LengthComparator implements Comparator<String> {

    @Override
    public int compare(String first, String second) {
        return Integer.compare(first.length(), second.length());
    }
}
```
>上述代码中Array.sort的第二个参数就是以面向对象的方式来传递一段代码，如何来进行排序

### java8的方式
```java
public class Test {
    public static void main(String[] args) {
        String[] strings = {"c1zzzz", "as", "bcc", "cxxq"};
        Comparator<String> compare =
                (String first, String second) -> Integer.compare(first.length(), second.length());
//        Comparator<String> compare =
//                (first, second) -> Integer.compare(first.length(), second.length());
        Arrays.sort(strings, compare);
        for (String i : strings) {
            System.out.println(i);
        }
    }
}
```
