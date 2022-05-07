package cn.example.ch9.semmantics;

/**
 * ProjectName: jvm
 * packageName: cn.example.ch9.semmantics
 * ClassName: FinalRefMemory
 *
 * @author: 李朋飞
 * @time: 2022/1/22 下午 09:20
 **/
public class FinalRefMemory {
    final int[] intArray;       //final 是引用类型
    static  FinalRefMemory obj;

    public FinalRefMemory() {   //构造函数
        this.intArray = new int[1]; //1
        intArray[0]=1;              //2
    }
    public static void writerOne(){obj=new FinalRefMemory();}//3

    public static void writerTwo(){obj.intArray[0]=3;} //4
    public static void reader(){        //读线程C执行
        if (obj!=null){                 //5
            int temp=obj.intArray[0]; //6
        }
    }
}
