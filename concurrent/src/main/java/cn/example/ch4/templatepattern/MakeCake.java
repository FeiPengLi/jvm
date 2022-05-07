package cn.example.ch4.templatepattern;


/**
 * ProjectName: jvm
 * packageName: cn.example.ch4.templatepattern
 * ClassName: MakeCake
 *
 * @author: 李朋飞
 * @time: 2022/1/3 15:08
 * 模板方法模式，生产蛋糕
 **/
public class MakeCake {
    public static void main(String[] args) {
        AbstractCake cake=new CheeseCake();
        AbstractCake cake2=new CreamCake();
        AbstractCake cake1=new SmallCake();
        cake.run();

        AbstractCake smcake=new SmallCake();
        smcake.run();
    }
}
