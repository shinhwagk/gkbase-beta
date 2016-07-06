```java
public class Test6 {
    public static void main(String[] args) throws InterruptedException {
        new A().doWork();
        while(true){
            Thread.sleep(1000);
        }
    }
}

class A{
    void doWork(){
        new Thread(()->{System.out.println(this.toString());}).start();
    }
    public String toString(){
        return "aaa";
    }
}
```
