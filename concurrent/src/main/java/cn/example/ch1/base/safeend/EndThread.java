package cn.example.ch1.base.safeend;

/**
 * ProjectName: jvm
 * packageName: cn.example.ch1.base.safeend
 * ClassName: EndThread
 *
 * @author: 李朋飞
 * @time: 2021/12/11 11:37
 *
 * 如何安全中断extendsThread类的线程
 **/
public class EndThread {

    static class UseThread extends Thread{
        public UseThread(String name) {
            super(name);
        }

        @Override
        public void run() {
            String threadName=Thread.currentThread().getName();
            System.out.println(threadName+" interrupt flag is 2 "+isInterrupted());
            while(!isInterrupted()){
//            while (!Thread.interrupted()){
//            while (!Thread.currentThread().isInterrupted()){
                System.out.println(threadName+" is running ");
                System.out.print(threadName+" interrupt flag is "+ isInterrupted()+" ");
            }
            System.out.println(threadName+ " interrupt flag is 1 "+isInterrupted());
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread thread=new UseThread("endThread");
        thread.start();
        Thread.sleep(10);
        thread.interrupt();//中断线程，其实设置线程的中断标志位为:true
    }


}
