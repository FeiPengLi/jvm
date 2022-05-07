package cn.example.ch6;

import cn.example.tools.SleepTools;

import java.security.SecureRandom;
import java.util.concurrent.*;

/**
 * ProjectName: jvm
 * packageName: cn.example.ch6
 * ClassName: UseThreadPool
 *
 * @author: 李朋飞
 * @time: 2022/1/3 21:10
 * 线程池使用范例
 **/
public class UseThreadPool {

    //无返回值
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
                SleepTools.ms(r.nextInt(100)*5);
            } catch (InterruptedException e) {
            }

        }
    }

    static class CallWorker implements Callable<String>{
        private String taksName;
        private SecureRandom r=new SecureRandom();

        public String getTaksName() {
            return taksName;
        }

        public CallWorker(String taksName) {
            this.taksName = taksName;
        }

        @Override
        public String call() throws Exception {
            System.out.println(Thread.currentThread().getName()+
                    " process the task : "+taksName);
            return Thread.currentThread().getName()+":"+r.nextInt(100)*5;
        }
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Runtime.getRuntime().availableProcessors();//逻辑核心
        ExecutorService thread=new ThreadPoolExecutor(2,
                4,3,TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(10),
                new ThreadPoolExecutor.AbortPolicy());
        ExecutorService threadPool= Executors.newFixedThreadPool(2);
        ExecutorService threadPool1= Executors.newSingleThreadExecutor();
        ExecutorService threadPool2= Executors.newCachedThreadPool();
        ExecutorService threadPool3= Executors.newScheduledThreadPool(2);
        ExecutorService threadPool4= Executors.newSingleThreadScheduledExecutor();
        for (int i = 0; i < 7; i++) {
            Worker worker=new Worker("Worker"+i);
            System.out.println("a new task has been added "+worker.getTaskname());
            threadPool.execute(worker);
        }

        for (int i = 0; i < 7; i++) {
            CallWorker callWorker=new CallWorker("CallWorker"+i);
            System.out.println("a new calltask has been added "+callWorker.getTaksName());
            Future<String >result=threadPool.submit(callWorker);
            System.out.println(result.get());
        }

        threadPool.shutdown();
        threadPool.shutdownNow();
    }
}
