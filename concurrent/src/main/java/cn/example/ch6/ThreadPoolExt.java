package cn.example.ch6;

import cn.example.tools.SleepTools;

import java.security.SecureRandom;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * ProjectName: jvm
 * packageName: cn.example.ch6
 * ClassName: ThreadPoolExt
 *
 * @author: 李朋飞
 * @time: 2022/1/3 18:51
 *
 * 扩展线程池使用范例
 **/
public class ThreadPoolExt {

    static class Worker implements Runnable{
        private String taskname;
        private SecureRandom r=new SecureRandom();

        public Worker(String taskname) {
            this.taskname = taskname;
        }

        public String getTaskname() {
            return taskname;
        }

        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName()+
                    " process the task : "+taskname);
            try {
                SleepTools.ms(r.nextInt(80)*8);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        ExecutorService threadpool=new ThreadPoolExecutor(2,4,3,
                TimeUnit.SECONDS,new ArrayBlockingQueue<Runnable>(10),
                new ThreadPoolExecutor.DiscardOldestPolicy()){
            @Override
            protected void beforeExecute(Thread t, Runnable r) {
                System.out.println("ready execute "+((Worker)r).getTaskname());
            }

            @Override
            protected void afterExecute(Runnable r, Throwable t) {
                System.out.println("Complete execute "+((Worker)r).getTaskname());
            }

            @Override
            protected void terminated() {
                System.out.println("线程池退出。。。");
            }
        };

        for (int i = 0; i <=6 ; i++) {
            Worker worker=new Worker("Worker_"+i);
            System.out.println("A new task has been added : "+worker.getTaskname());
            threadpool.execute(worker);

        }
        threadpool.shutdown();
    }
}
