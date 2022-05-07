package cn.example.ch2.tools;

import java.util.Map;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CyclicBarrier;

/**
 * ProjectName: jvm
 * packageName: cn.example.ch2.tools
 * ClassName: useCyclicBarrier
 *
 * @author: 李朋飞
 * @time: 2021/12/25 15:50
 *CyclicBarrier的用法
 **/
public class useCyclicBarrier {

    private  static CyclicBarrier barrier=new CyclicBarrier(4,new CollectThread());

    //存放子线程工作结果的容器
    private static ConcurrentHashMap<String,Long>resultMap
            =new ConcurrentHashMap<>();

    public static void main(String[] args) {
        for (int i = 0; i <4; i++) {
            Thread thread=new Thread(new SubThread());
            thread.start();
        }
    }
    private static class CollectThread implements Runnable{
        @Override
        public void run() {
            StringBuilder result=new StringBuilder();
            for (Map.Entry<String,Long> workResult:resultMap.entrySet()){
                result.append("{"+workResult.getValue()+"}");
            }
            System.out.println(" the result="+ result);
            System.out.println("do other business......");
        }
    }
    private static class SubThread implements Runnable{
        @Override
        public void run() {
            long id=Thread.currentThread().getId();
            resultMap.put(id+"",id);
            try {
                Thread.sleep(1000+id);
                System.out.println("Thread_"+id+" ......do something");
                barrier.await();
                Thread.sleep(1000+id);
                System.out.println("Thread_"+id+" .......do its business");
               // barrier.await();
            } catch (InterruptedException | BrokenBarrierException e) {
                e.printStackTrace();
            }
        }
    }
}
