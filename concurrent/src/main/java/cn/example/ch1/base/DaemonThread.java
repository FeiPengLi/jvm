package cn.example.ch1.base;

/**
 * ProjectName: jvm
 * packageName: cn.example.ch1.base
 * ClassName: DaemonThread
 *
 * @author: 李朋飞
 * @time: 2021/12/11 10:58
 * 守护线程使用
 **/
public class DaemonThread {

    private static class UseThread extends Thread{
        @Override
        public void run() {
            try {
                while (true){

                    System.out.println(Thread.currentThread().getName()+" iam extends Thread");
                    System.out.println(Thread.currentThread().getName()+" interrupt flag is "+isInterrupted());
                }
//                该语句无意义：while(true)写法导致。改为：while(!Thread.currentThread().isInterrupted())即可。
//                System.out.println(Thread.currentThread().getName()+" interrupt flag is "+isInterrupted());

            }finally {
                //守护线程中finally不一定起作用
                System.out.println(".....finally");
            }

        }
    }

    static {
        UseThread useThread=new UseThread();
        useThread.setDaemon(true);
        useThread.start();
        System.out.println("111111 " +(useThread.isInterrupted()==false?false:true));
    }

    public static void main(String[] args) throws InterruptedException {
//        UseThread useThread=new UseThread();
//        useThread.setDaemon(true);
//        useThread.start();
        Thread.sleep(2000);
//        System.out.println(" sfs "+useThread.isInterrupted());
//        useThread.interrupt();
//        System.out.println(" sfs "+useThread.isInterrupted());
    }
}
