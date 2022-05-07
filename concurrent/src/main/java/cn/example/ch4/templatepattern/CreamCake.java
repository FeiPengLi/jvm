package cn.example.ch4.templatepattern;

/**
 * ProjectName: jvm
 * packageName: cn.example.ch4.templatepattern
 * ClassName: CreamCake
 *
 * @author: 李朋飞
 * @time: 2022/1/3 15:07
 **/
public class CreamCake extends AbstractCake{
    @Override
    protected void shape() {
        System.out.println("奶油蛋糕造型");
    }

    @Override
    protected void apply() {
        System.out.println("奶油蛋糕涂抹");
    }

    @Override
    protected void brake() {
        System.out.println("奶油蛋糕烘培");
    }
}
