package cn.example.ch9.semmantics;

/**
 * ProjectName: jvm
 * packageName: cn.example.ch9.semmantics
 * ClassName: FinalMeemory
 *
 * @author: 李朋飞
 * @time: 2022/1/22 下午 09:13
 * final内存语义
 **/
public class FinalMeemory {
    int i;          //普通变量
    final int j;    //final变量
    static FinalMeemory obj;

    public FinalMeemory() { //构造函数
        this.i = 1;         //写普通域
        this.j = 2;         //写final域
    }

    public static void writer(){obj=new FinalMeemory();}

    public static void reader(){    //读线程B执行
        FinalMeemory object=obj;    //读对象引用
        int a=object.i;             //读普通域
        int b=object.j;             //读final域
    }
}
