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
-　上述代码中ｓｓｓ
