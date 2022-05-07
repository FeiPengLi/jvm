package cn.example.ch6;

import cn.example.tools.SleepTools;

import java.security.SecureRandom;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * ProjectName: jvm
 * packageName: cn.example.ch6
 * ClassName: ThreadPoolAdv
 *
 * @author: 李朋飞
 * @time: 2022/1/3 18:32
 *
 * 自定义线程池中线程的创建方式，把线程设置为守护线程
 **/
public class ThreadPoolAdv {

    static class Worker implements Runnable{
        private String taskName;
        private SecureRandom r=new SecureRandom();

        public Worker(String taskName) {
            this.taskName = taskName;
        }

        public String getTaskName() {
            return taskName;
        }

        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName()+" process the task : "+taskName);
            try {
                SleepTools.ms(r.nextInt(100)*5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    static class MyThreadFactory implements ThreadFactory{

        private AtomicInteger count=new AtomicInteger(0);
        @Override
        public Thread newThread(Runnable r) {
            Thread t=new Thread(r,"Makr_"+count.getAndIncrement()   );
            t.setDaemon(true);
            System.out.println("create "+t);
            return t;
        }
    }


    public static void main(String[] args) throws InterruptedException {
        ExecutorService threadPool=new ThreadPoolExecutor(2,4,3,
                TimeUnit.SECONDS,new ArrayBlockingQueue<>(10),
                new MyThreadFactory(),new ThreadPoolExecutor.DiscardOldestPolicy());

        for (int i = 0; i < 7; i++) {
            Worker worker=new Worker("worker"+i);
            System.out.println(" a new task has been added: "+worker.getTaskName());
            threadPool.execute(worker);
        }
        threadPool.shutdown();
        Thread.sleep(6000);

    }
}
