package org.example.ex2;

/**
 * packageName:org.example.ex2
 * author:李朋飞
 * time:2021/11/30 21:53
 * ProjectName:jvm
 * ClassName: StackOverFlow
 * 栈溢出 -Xss=1m 默认也是1m
 * java.lang.StackOverflowError
 */
public class StackOverFlow {

    public void fly(){
        fly();
    }

    public static void main(String[] args) {
        StackOverFlow overFlow=new StackOverFlow();
        overFlow.fly();
    }
}
