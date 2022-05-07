package cn.example.ch4.templatepattern;

/**
 * ProjectName: jvm
 * packageName: cn.example.ch4.templatepattern
 * ClassName: MouseCake
 *
 * @author: 李朋飞
 * @time: 2022/1/3 15:09
 **/
public class MouseCake extends AbstractCake{
    @Override
    protected void shape() {
        System.out.println("慕斯蛋糕");
    }

    @Override
    protected void apply() {
        System.out.println("慕斯蛋糕涂抹");
    }

    @Override
    protected void brake() {
        System.out.println("慕斯蛋糕烘培");
    }
}
