package cn.example.ch2.tools;

import cn.example.tools.SleepTools;

import java.util.concurrent.CountDownLatch;

/**
 * ProjectName: jvm
 * packageName: cn.example.ch2.tools
 * ClassName: useCountDownLatch
 *
 * @author: 李朋飞
 * @time: 2021/12/25 15:22
 * CountDownLatch用法
 **/
public class useCountDownLatch {
    static CountDownLatch latch=new CountDownLatch(6);

    private static class InitThread implements Runnable{
        @Override
        public void run() {
            System.out.println("Thread_"+Thread.currentThread().getId()+
                    " ready init work...");
            latch.countDown();
            for (int i = 0; i < 2; i++) {
                System.out.println("Thread_"+Thread.currentThread().getId() +
                        "...continue do its work");


            }
        }
    }
    private static class BusinessThread implements Runnable{
        @Override
        public void run() {
            try {
                latch.await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            for (int i = 0; i < 3; i++) {
                System.out.println("BusinessThread_"+Thread.currentThread().getId()
                +" do business...");
            }
        }
    }

    public static void main(String[] args) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    SleepTools.ms(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Thread_"+Thread.currentThread().getId()
                +" ready init work step 1st...");

                latch.countDown();
                System.out.println(" begin step 2nd");
                try {
                    SleepTools.ms(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("Thread_"+Thread.currentThread().getId()+
                        " ready init work step 2nd");
                latch.countDown();
            }
        }).start();

        new Thread(new BusinessThread()).start();

        for (int i = 0; i <=3; i++) {
            Thread thread =new Thread(new InitThread());
            thread.start();

        }

        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(" main method do its work....");
    }
}
