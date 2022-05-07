package cn.example.ch6.mypool;

import java.util.Arrays;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * ProjectName: jvm
 * packageName: cn.example.ch6.mypool
 * ClassName: MyThreadPool2
 *
 * @author: 李朋飞
 * @time: 2022/1/15 上午 09:08
 * 自定义线程池实现
 **/
public class MyThreadPool2 {

    private static int WORK_COUNT=5;
    private final BlockingQueue<Runnable>taskQueue;
    //工作线程
    private WorkThread[]workThreads;
    private final int work_number;


    public MyThreadPool2() {
       this(100,WORK_COUNT);
    }

    public MyThreadPool2(int task_count,
                         int work_number) {
        if (work_number<=0)work_number=WORK_COUNT;
        if (task_count<=0)task_count=100;
        this.taskQueue = new ArrayBlockingQueue<>(task_count);
        this.work_number = work_number;
        workThreads=new WorkThread[work_number];
        for (int i = 0; i <work_number; i++) {
            workThreads[i]=new WorkThread();
            workThreads[i].start();
        }
    }
    //销毁线程池
    public void destroy(){
        System.out.println("ready close pool.....");
        for (int i = 0; i < work_number; i++) {
            workThreads[i].stopWorker();
            workThreads[i]=null;//help gc
        }
        taskQueue.clear();
    }
    //加入任务，但是任务只是加入队列
    public void execute(Runnable task){
        try {
            taskQueue.put(task);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        return "MyThreadPool2{" +
                ", work_number=" + work_number +
                ", wait task number=" + taskQueue.size() +
                '}';
    }

    //内部类，工作线程实现
    private class WorkThread extends Thread{
        @Override
        public void run() {
            Runnable r=null;
            try {
                while (!isInterrupted()){
                    r=taskQueue.take();
                    if (r!=null){
                        System.out.println(getId()+" ready execute"+
                                ((TestMyThreadPool.MyTask)r).getName());
                        r.run();//刚开始没写这个，导致任务未执行
                    }
                    r=null;
                }
            } catch (InterruptedException e) {
//                e.printStackTrace();
            }
        }
        //停止工作
        public void stopWorker(){
            interrupt();
        }
    }
}
