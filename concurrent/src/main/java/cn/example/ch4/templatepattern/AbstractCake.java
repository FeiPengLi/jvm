package cn.example.ch4.templatepattern;

/**
 * ProjectName: jvm
 * packageName: cn.example.ch4.templatepattern
 * ClassName: AbstractCake
 *
 * @author: 李朋飞
 * @time: 2022/1/3 15:01
 *
 * 抽象类蛋糕模型
 **/
public abstract class AbstractCake {
    protected abstract void shape();
    protected abstract void apply();
    protected abstract void brake();

    //模板方法
    public final void run(){
        this.shape();
        if (this.shouldApply()){
            this.apply();
        }
        this.brake();
    }
    protected boolean shouldApply(){return true;}
}
