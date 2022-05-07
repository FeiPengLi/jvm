package cn.example.ch1.synchronizeds;

import cn.example.tools.SleepTools;

/**
 * ProjectName: jvm
 * packageName: cn.example.ch1.synchronizeds
 * ClassName: InstanceAndClass
 *
 * @author: 李朋飞
 * @time: 2021/12/11 16:52
 *
 * 实列锁和类锁是不同的，两者可以并行；
 **/
public class InstanceAndClass {

    private static class SyncClass extends Thread{
        @Override
        public void run() {
            System.out.println("TestClass is running.....");
            try {
                synClass();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
    private static class InstanceSyn implements Runnable{
        private InstanceAndClass instanceAndClass;

        public InstanceSyn(InstanceAndClass instanceAndClass) {
            this.instanceAndClass = instanceAndClass;
        }

        @Override
        public void run() {
            System.out.println("TestInstance is running....."+instanceAndClass);
            try {
                instanceAndClass.instance();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    //类锁
    private static synchronized void synClass() throws InterruptedException {
        SleepTools.second(1);
        System.out.println("synClass is going...");
        SleepTools.second(1);
        System.out.println("synClas is ended...");
    }
    //实例锁
    private synchronized void instance() throws InterruptedException {
        SleepTools.second(1);
        System.out.println("synInstance is going..."+this.toString());
        SleepTools.second(1);
        System.out.println("synInstance is ended "+ this.toString());
    }


    public static void main(String[] args) {
        InstanceAndClass instanceAndClass=new InstanceAndClass();
        Thread t=new SyncClass();
        Thread t2=new Thread(new InstanceSyn(instanceAndClass));
        t2.start();
        try {
            SleepTools.second(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        t.start();
    }
}
