package cn.example.ch2.forkjoin.sum;

import cn.example.tools.SleepTools;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;

/**
 * ProjectName: jvm
 * packageName: cn.example.ch2.forkjoin.sum
 * ClassName: SumArray
 *
 * @author: 李朋飞
 * @time: 2021/12/25 14:10
 *
 * ForkJoin执行累加
 **/
public class SumArray {
    private static class SumTask extends RecursiveTask<Integer>{
        private final static int THRESHOLD=MakeArray.ARRAY_LENGTH/10;//阈值
        private int[]src;
        private int fromIndex;
        private int toIndex;

        public SumTask(int[] src, int fromIndex, int toIndex) {
            this.src = src;
            this.fromIndex = fromIndex;
            this.toIndex = toIndex;
        }

        @Override
        protected Integer compute() {
            //任务大小是否合适
            if (toIndex-fromIndex<THRESHOLD){
                int count=0;
                for (int i=fromIndex;i<=toIndex;i++){
                    try {
                        SleepTools.ms(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    count=count+src[i];
                }
                return count;
            }else{
                int mid=(fromIndex+toIndex)/2;
                SumTask left=new SumTask(src,fromIndex,mid);
                SumTask right=new SumTask(src,mid+1,toIndex);
                invokeAll(left,right);
                return left.join()+right.join();
            }
        }
    }

    public static void main(String[] args) {
        int[]src=MakeArray.makeArray();
        ForkJoinPool pool=new ForkJoinPool();
        SumTask innerFind=new SumTask(src,0,src.length-1);
        long start=System.currentTimeMillis();
        pool.invoke(innerFind);
        System.out.println("the count is "+innerFind.join()+" spend time:"+(System.currentTimeMillis()-start)+"ms");
    }
}
