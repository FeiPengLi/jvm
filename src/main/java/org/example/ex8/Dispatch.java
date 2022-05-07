package org.example.ex8;

/**
 * packageName:org.example.ex8
 * author:李朋飞
 * time:2021/12/4 15:36
 * ProjectName:jvm
 * ClassName: Dispatch
 * 虚方法
 * 静态分派
 * 动态分派
 */
public class Dispatch {
    static class QQ{}
    static class WX{}
    public static class Father{
        public void hardChoice(QQ arg){
            System.out.println(" father choose qq");
        }
        public void hardChoice(WX arg){
            System.out.println(" father choose weixin");
        }

    }

    public static class Son extends Father{
        @Override
        public void hardChoice(QQ arg) {
            System.out.println( " son choose qq ...");
        }

        @Override
        public void hardChoice(WX arg) {
            System.out.println(" son choose weixin ...");
        }
    }

    public static void main(String[] args) {
        Father f=new Father();
        Father s=new Son();
        f.hardChoice(new WX());
        s.hardChoice(new QQ());
    }


}
