package org.example.ex8;

/**
 * ProjectName: jvm
 * packageName: org.example.ex8
 * ClassName: StaticDispatch
 * 静态分派：方法的重载--编译阶段
 * 类生命周期：1  加载，连接（验证，准备，解析），初始化，使用，卸载
 * @author: 李朋飞
 * @time: 2021/12/4 17:05
 **/
public class StaticDispatch {

    static abstract class Human{}
    static class Man extends Human{}
    static class Woman extends Human{}

    public void sayHello(Human guy){
        System.out.println("hello,guy!");
    }
    public void sayHello(Man guy){
        System.out.println("hello,Man");
    }
    public void sayHello(Woman guy){
        System.out.println("hello,woman");
    }

    public static void main(String[] args) {
        StaticDispatch sr=new StaticDispatch();

        Human man=new Man();
        Human woman=new Woman();
        sr.sayHello(man);
        sr.sayHello(woman);
        System.out.println(man.toString());
        System.out.println(woman.toString());

        Human human=new Man();
        sr.sayHello((Man) human);
        System.out.println(human.toString());
        human=new Woman();
        sr.sayHello((Woman) human);
        System.out.println(human.toString());
    }
}
