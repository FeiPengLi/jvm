package cn.example.ch7.dcl;

/**
 * ProjectName: jvm
 * packageName: cn.example.ch7.dcl
 * ClassName: SingleEhan
 *
 * @author: 李朋飞
 * @time: 2022/1/16 上午 10:35
 *
 * 饿汉模式 单例
 * 枚举
 **/
public class SingleEhan {
    //私有化
    private SingleEhan(){}
    private static SingleEhan singleEhan=new SingleEhan();
}
