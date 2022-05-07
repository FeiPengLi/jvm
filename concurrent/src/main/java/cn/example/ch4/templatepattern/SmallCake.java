package cn.example.ch4.templatepattern;

/**
 * ProjectName: jvm
 * packageName: cn.example.ch4.templatepattern
 * ClassName: SmallCake
 *
 * @author: 李朋飞
 * @time: 2022/1/3 15:10
 **/
public class SmallCake extends AbstractCake{
    private boolean flag=false;

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    @Override
    protected void shape() {
        System.out.println("小蛋糕造型");
    }

    @Override
    protected void apply() {
        System.out.println("小蛋糕涂抹");
    }

    @Override
    protected void brake() {
        System.out.println("小蛋糕烘培");
    }
}
