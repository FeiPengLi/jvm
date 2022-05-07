package cn.example.ch1.synchronizeds;

/**
 * ProjectName: jvm
 * packageName: cn.example.ch1.synchronizeds
 * ClassName: TestIntegerSync
 *
 * @author: 李朋飞
 * @time: 2021/12/11 21:50
 * 错误的加锁和原因分析
 **/
public class TestIntegerSync {
    public static void main(String[] args) {
        Worker worker=new Worker(1);
        for (int i = 0; i < 5; i++) {
            new Thread(worker).start();

        }
    }
    private static class Worker implements Runnable{

        private Integer i;
        private Object o=new Object();

        public Worker(Integer i) {
            this.i = i;
        }

        @Override
        public void run() {
            synchronized (i){//错误的加锁每次的加锁对象不是同一个。5个线程锁了5个不同的对象，正确的该锁 o 对象
                Thread thread=Thread.currentThread();
                System.out.println(thread.getName()+" -- "+System.identityHashCode(i));
                i++;
                System.out.println(thread.getName()+"---------"+i+"--@"+System.identityHashCode(i));
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(thread.getName()+"------"+i+"--!@"+System.identityHashCode(i));
            }
        }
    }
}
