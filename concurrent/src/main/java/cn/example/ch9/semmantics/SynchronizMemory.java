package cn.example.ch9.semmantics;

import cn.example.tools.SleepTools;

/**
 * ProjectName: jvm
 * packageName: cn.example.ch9.semmantics
 * ClassName: SynchronizMemory
 *
 * @author: 李朋飞
 * @time: 2022/1/22 下午 09:26
 *
 * 锁的内存语义
 **/
public class SynchronizMemory {

    private static boolean ready;
    private static int number;

    private static class PrintThread extends Thread{
        @Override
        public void run() {
            while(!ready){
                System.out.println("number="+number);
            }
            System.out.println("number======="+number);
        }
    }

    public static void main(String[] args) {
        new PrintThread().start();
        try {
            SleepTools.second(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        number=50;
        ready=true;
        try {
            SleepTools.second(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println(" main is ended!");
    }
}
