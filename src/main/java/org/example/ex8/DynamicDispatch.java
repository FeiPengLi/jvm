package org.example.ex8;

/**
 * packageName:org.example.ex8
 * author:李朋飞
 * time:2021/12/4 15:58
 * ProjectName:jvm
 * ClassName: DynamicDispatch
 * 虚方法：动态分派（多态）
 */
public class DynamicDispatch {

    static abstract class Virus{//病毒
        protected abstract void ill();//生病
    }

    static class Cold extends Virus{
        @Override
        protected void ill() {
            System.out.println(" 感冒了，！");
        }
    }


    static class CoronaVirus extends Virus{
        @Override
        protected void ill() {
            System.out.println(" 新冠，请戴好口罩！");
        }
    }

    public static void main(String[] args) {
        Virus clod=new Cold();
        clod.ill();
        System.out.println(clod.hashCode());
        clod=new CoronaVirus();//多态
        System.out.println(clod.hashCode());
        clod.ill();
    }


}
