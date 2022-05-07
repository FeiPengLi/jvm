package cn.example.ch7.dcl;

/**
 * ProjectName: jvm
 * packageName: cn.example.ch7.dcl
 * ClassName: SingleDcl
 *
 * @author: 李朋飞
 * @time: 2022/1/16 上午 10:28
 *
 * 懒汉模式-双重检查
 **/
public class SingleDcl {
    //加volatile禁止指令重排序
    private volatile static SingleDcl singleDcl;
    //私有化
    private SingleDcl(){}

    public static SingleDcl getInstance(){
        if(singleDcl==null){
            System.out.println(Thread.currentThread()+" is null");
            synchronized (SingleDcl.class){
                if (singleDcl==null){
                    System.out.println(Thread.currentThread()+" is null");
                    //内存中分配空间 1 singleDcl加volatile防止这块执行顺序
//                    空间初始化 2
//                    把这个空间的地址给我们引用 3
                    singleDcl=new SingleDcl();
                }
            }
        }
        return singleDcl;
    }
}
