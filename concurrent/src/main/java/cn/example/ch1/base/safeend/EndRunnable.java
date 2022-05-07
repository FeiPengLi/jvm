package cn.example.ch1.base.safeend;

/**
 * ProjectName: jvm
 * packageName: cn.example.ch1.base.safeend
 * ClassName: EndRunnable
 *
 * @author: 李朋飞
 * @time: 2021/12/11 11:22
 * 如何中断实现runnable接口的线程
 **/
public class EndRunnable {
    static class UseRunnable implements Runnable{
        //Thread.currentThread().isInterrupted()==false:当前线程未被中断，等于 true 表示被打断了
        @Override
        public void run() {
            //若线程没有被中断，则一直执行
            while(!Thread.currentThread().isInterrupted()){
                System.out.println(Thread.currentThread().getName()+"" +
                        " I am implements Runnable 线程标志位："+Thread.currentThread().isInterrupted());
            }
            System.out.println(Thread.currentThread().getName()+"" +
                    " I am implements Runnable interrupt flag is :"+Thread.currentThread().isInterrupted());


        }

    }

    public static void main(String[] args) throws InterruptedException {
        UseRunnable useRunnable=new UseRunnable();
        Thread thread = new Thread(useRunnable, "endThreadMethod");
        thread.start();
        Thread.sleep(20);
        thread.interrupt();//中断

    }
}
