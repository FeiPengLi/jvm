package cn.example.ch4.templatepattern;

/**
 * ProjectName: jvm
 * packageName: cn.example.ch4.templatepattern
 * ClassName: CheeseCake
 *
 * @author: 李朋飞
 * @time: 2022/1/3 15:05
 * 芝士蛋糕
 **/
public class CheeseCake  extends AbstractCake{
    @Override
    protected void shape() {
        System.out.println("芝士蛋糕造型");
    }

    @Override
    protected void apply() {
        System.out.println("芝士蛋糕涂抹");
    }

    @Override
    protected void brake() {
        System.out.println("芝士蛋糕烘培");
    }
}
