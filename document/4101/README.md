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

### java8的方式 lambda表达式
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
>上述代码同样是传递一段代码给Array.sort的第二个参数，我的理解方式是，说白了第一段代码Comparator.compare这个接口就是传入2个参数，传出一个int，这里的lambda同样做到了这一点。
>此外Array.sort的第二个参数需要一个Comparator<T>的这么一个类型，在上面这个表达式中变量compare的类型就是Comparator
