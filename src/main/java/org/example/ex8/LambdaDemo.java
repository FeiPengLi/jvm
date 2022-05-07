package org.example.ex8;

/**
 * ProjectName: jvm
 * packageName: org.example.ex8
 * ClassName: LambdaDemo
 *
 * @author: 李朋飞
 * @time: 2021/12/4 16:41
 **/
public class LambdaDemo {
    public static void main(String[] args) {
        Runnable r=()-> System.out.println("Hello Lambda!");
        r.run();
    }
}
