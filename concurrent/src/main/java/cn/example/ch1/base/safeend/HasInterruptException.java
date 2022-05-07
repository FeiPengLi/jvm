package cn.example.ch1.base.safeend;

/**
 * ProjectName: jvm
 * packageName: cn.example.ch1.base.safeend
 * ClassName: HasInterruptException
 *
 * @author: 李朋飞
 * @time: 2021/12/11 12:05
 * 阻塞方法中抛出InterruptedException异常后，如果需要持续中断，需要手动再中断一次
 **/
public class HasInterruptException {
    private static class UseThread extends Thread{
        public UseThread(String name) {
            super(name);
        }

        @Override
        public void run() {
            while(!Thread.currentThread().isInterrupted()){
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    //抛出异常不会中断执行，，需要再次调用interrupt();才可停止
                    System.out.println(Thread.currentThread().getName()+
                            " in interruptedException interrupt flag1 is "+Thread.currentThread().isInterrupted());
                    e.printStackTrace();
                    interrupt();//调用interrupt();
                }
                System.out.println(Thread.currentThread().getName()
                        + " I am extends Thread.");
            }
            //不手动调用interrupt(),该语句不会执行。
            System.out.println(Thread.currentThread().getName()+" interrupt flag is "+Thread.currentThread().isInterrupted());

        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread endThread=new UseThread("hasInterException");
        endThread.start();
        Thread.sleep(600);
        endThread.interrupt();

    }
}
