package org.example.ex10;

/**
 * ProjectName: jvm
 * packageName: org.example.ex10
 * ClassName: NormalDeadLock
 *
 * @author: 李朋飞
 * @time: 2021/12/4 22:01
 * 死锁问题
 **/
public class NormalDeadLock {

    public static Object n1=new Object();
    public static Object n2=new Object();

    public static void peter() throws InterruptedException {
        String ThreadName=Thread.currentThread().getName();
        synchronized (n1){
            System.out.println(ThreadName+" get n1 lock");
            Thread.sleep(100);
            synchronized(n2){
                System.out.println(ThreadName+" get n2 lock");
            }
        }
    }
    public static void king() throws InterruptedException {
        String threadName=Thread.currentThread().getName();
        synchronized(n2){
            System.out.println(threadName+ " get n2 lock");
            Thread.sleep(100);
            synchronized(n1){
                System.out.println(threadName+ " get n1 lock");
            }
        }
    }
    public static class subThread extends Thread{

        public String name;
        public subThread(String name){
            this.name=name;
        }
        @Override
        public void run() {
           Thread.currentThread().setName(name);
            try {
                peter();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        Thread.currentThread().setName("king");
        subThread peter=new subThread("peter");
        peter.start();
        king();
    }
}
