package cn.example.ch6.mypool;

import java.util.Random;

/**
* ProjectName: jvm
* packageName: cn.example.ch6.mypool
* ClassName: TestMyThreadPool
* @author: 李朋飞
* @time: 2022/1/15 上午 09:42
**/public class TestMyThreadPool {
    public static void main(String[] args) throws InterruptedException {
        //创建3个线程的线程池
        MyThreadPool2 t=new MyThreadPool2(0,3);
        t.execute(new MyTask("taskA"));
        t.execute(new MyTask("taskB"));
        t.execute(new MyTask("taskC"));
        t.execute(new MyTask("taskD"));
        t.execute(new MyTask("taskE"));
        System.out.println(t);
        Thread.sleep(10000);
        t.destroy();//所有线程执行完成才destroy
        System.out.println(t);
    }
    static class MyTask implements Runnable{

        private String name;

        private Random r=new Random();

        public MyTask(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }
        //执行任务
        @Override
        public void run() {
            try {
                Thread.sleep(r.nextInt(1000)+2000);
            } catch (InterruptedException e) {
                System.out.println(Thread.currentThread().getId()+
                        " sleep interruptExeption:"+
                        Thread.currentThread().isInterrupted());
            }
            System.out.println("任务："+name+" 执行完成!");
        }
    }
}
