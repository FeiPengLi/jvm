package cn.example.ch7;

/**
 * ProjectName: jvm
 * packageName: cn.example.ch7
 * ClassName: NormalDeadLock
 *
 * @author: 李朋飞
 * @time: 2022/1/15 下午 08:04
 *
 * 简单死锁演示
 **/
public class NormalDeadLock {
    private static Object valueFirst=new Object();//第一个锁
    private static Object valueSecond=new Object();//第二把锁

    private static void firstToSecond() throws InterruptedException{
        String threadname=Thread.currentThread().getName();
        synchronized (valueFirst){
            System.out.println(threadname+" get 1st");
            Thread.sleep(100);
            synchronized (valueSecond){
                System.out.println(threadname+" get 2nd");
            }
        }
    }

    private static void SecondToFirst()throws InterruptedException{
        String threadName=Thread.currentThread().getName();
        synchronized (valueSecond){
            System.out.println(threadName+" get 2nd");
            Thread.sleep(100);
            synchronized (valueFirst){

                System.out.println(threadName+" get 1st");
            }
        }
    }

    private static class TestThread extends Thread{
        private String name;

        public TestThread(String name) {
            this.name = name;
        }

        @Override
        public void run() {
            Thread.currentThread().setName(name);
                try {
                    SecondToFirst();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
        }
    }

    public static void main(String[] args) {
        Thread.currentThread().setName("TestDealock");
        TestThread testThread=new TestThread("subTestThread");
        testThread.start();
        try {
            firstToSecond();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
