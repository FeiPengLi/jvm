package org.example.ex1;

/**
 * packageName:org.example.ex1
 * author:李朋飞
 * time:2021/11/28 18:54
 * ProjectName:jvm
 * ClassName: MethodAndStack
 * JAVA方法的运行与虚拟机栈
 */
public class MethodAndStack {

    public static void main(String[] args) {
        A();
    }
    public static void A(){
        B();
    }
    public static void B(){
        C();
    }
    public static void C(){

    }
}
