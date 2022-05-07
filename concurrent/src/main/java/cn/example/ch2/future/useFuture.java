package cn.example.ch2.future;

import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

/**
 * ProjectName: jvm
 * packageName: cn.example.ch2.future
 * ClassName: useFuture
 *
 * @author: 李朋飞
 * @time: 2021/12/25 18:54
 *  Future的使用
 **/
public class useFuture {

    private static class UseCallAble implements Callable<Integer>{
        private int sum;
        @Override
        public Integer call() throws Exception {
            System.out.println("Callable开始计算：");
            for (int i = 0; i < 5000; i++) {
                if (Thread.currentThread().isInterrupted()){
                    System.out.println("Callable计算任务中断！");
                    return null;
                }
                sum=sum+i;
                System.out.println("sum=    "+sum);
            }
            System.out.println("Callable计算结束：sum="+sum);
            return sum;
        }
    }

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        UseCallAble useCallAble=new UseCallAble();
        FutureTask<Integer> futureTask=new FutureTask<>(useCallAble);
        new Thread(futureTask).start();
        Thread.sleep(1);
        Random r=new Random();
        if (r.nextInt(100)>50){
            System.out.println("get UseCallable result = "+ futureTask.get());
        }else{
            System.out.println("cancel..................");
            futureTask.cancel(true);
        }
    }
}
