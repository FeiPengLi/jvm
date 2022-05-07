package org.example.ex1;

/**
 * packageName:org.example.ex1
 * author:李朋飞
 * time:2021/11/28 18:42
 * ProjectName:jvm
 * ClassName: StackError
 * 虚拟机栈溢出 -Xss 默认1M
 * Error: at org.example.ex1.StackError.A(StackError.java:20)
 */
public class StackError {

    public static void main(String[] args) {
        A();

    }

    public static void A(){
            A();
    }
}
