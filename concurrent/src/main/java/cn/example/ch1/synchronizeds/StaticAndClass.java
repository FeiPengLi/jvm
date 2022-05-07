package cn.example.ch1.synchronizeds;

import cn.example.tools.SleepTools;

/**
 * ProjectName: jvm
 * packageName: cn.example.ch1.synchronizeds
 * ClassName: StaticAndClass
 *
 * @author: 李朋飞
 * @time: 2021/12/11 17:20
 *
 * 类锁和锁satatic变量也是不同的
 */
public class StaticAndClass {

    private static class synClass extends Thread{
        @Override
        public void run() {
            System.out.println(currentThread().getName()+": SynClass is running...");
            syncClass();
        }
    }
    private static class SyncStaic extends Thread{
        @Override
        public void run() {
            System.out.println(currentThread().getName()+": SyncStatic is running...");
            synStatic();
        }
    }
    public static  synchronized void syncClass(){
        System.out.println(Thread.currentThread().getName()+": syncClass going...");
        try {
            SleepTools.second(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName()+": syncClass end...");
    }

    private static Object obj=new Object();
    private static void synStatic(){
        synchronized (obj){
            System.out.println(Thread.currentThread().getName()+"synStatic going....");
            try {
                SleepTools.second(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName()+"synStatic end...");
        }
    }


    public static void main(String[] args) {
        StaticAndClass staticAndClass=new StaticAndClass();
        Thread t1=new SyncStaic();
        Thread t2=new synClass();
        t1.start();
        try {
            SleepTools.second(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        t2.start();
    }
}
