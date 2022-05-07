package cn.example.ch7.dcl;

/**
 * ProjectName: jvm
 * packageName: cn.example.ch7.dcl
 * ClassName: SingleInit
 *
 * @author: 李朋飞
 * @time: 2022/1/16 上午 10:37
 *
 * 懒汉试  -  延迟初始化占位类模式
 **/
public class SingleInit {
    //私有化
    private SingleInit(){}

    private static class InstanceHolder{
        private static SingleInit instance=new SingleInit();
    }
    public static SingleInit getInstance(){
        return InstanceHolder.instance;
    }
}
