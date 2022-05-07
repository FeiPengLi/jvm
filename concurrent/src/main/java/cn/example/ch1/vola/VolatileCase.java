package cn.example.ch1.vola;

import cn.example.tools.SleepTools;

/**
 * ProjectName: jvm
 * packageName: cn.example.ch1.vola
 * ClassName: VolatileCase
 *
 * @author: 李朋飞
 * @time: 2021/12/12 17:57
 * Volatile:可见性
 **/
public class VolatileCase {

    //不加 volatile main线程结束了程序也不会停止；
    private volatile static boolean  ready;

    private static int number;

    private static class PrintThread extends Thread{
        @Override
        public void run() {
            System.out.println("PrintThread is running .......");
            while (!ready);
            System.out.println("number = "+number);
        }
    }

    public static void main(String[] args) {
        new PrintThread().start();
        try {
            SleepTools.second(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        number=40;
        ready=true;
        try {
            SleepTools.second(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("main Thread is ended!");
    }
}
