package cn.example.ch2.forkjoin.sum;

import cn.example.tools.SleepTools;

/**
 * ProjectName: jvm
 * packageName: cn.example.ch2.forkjoin.sum
 * ClassName: SumNormal
 *
 * @author: 李朋飞
 * @time: 2021/12/25 14:10
 * 单线程执行累加
 **/
public class SumNormal {

    public static void main(String[] args) throws InterruptedException {
        int count=0;
        int[]src=MakeArray.makeArray();
        long start=System.currentTimeMillis();
        for (int i=0;i<src.length;i++){
            SleepTools.ms(1);
            count=count+src[i];
        }
        System.out.println("the count is "+count+" the spend time:"+(System.currentTimeMillis()-start)+"ms");
    }
}
