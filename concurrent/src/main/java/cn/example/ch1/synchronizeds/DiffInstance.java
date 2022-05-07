package cn.example.ch1.synchronizeds;

import cn.example.tools.SleepTools;

/**
 * ProjectName: jvm
 * packageName: cn.example.ch1.synchronizeds
 * ClassName: DiffInstance
 *
 * @author: 李朋飞
 * @time: 2021/12/11 16:10
 *
 * 锁的实例不一样，也是可以并行的
 **/
public class DiffInstance {

    private static class InstanceSync implements Runnable{
        private DiffInstance diffInstance;

        public InstanceSync(DiffInstance diffInstance) {
            this.diffInstance = diffInstance;
        }

        @Override
        public void run() {
            System.out.println("TestInstance is running...."+diffInstance);
            diffInstance.instance();
        }

        @Override
        public String toString() {
            return "InstanceSync{" +
                    "diffInstance=" + diffInstance +
                    '}';
        }
    }

    private static class Instance2Syn implements Runnable{
        private DiffInstance diffInstance;

        public Instance2Syn(DiffInstance diffInstance) {
            this.diffInstance = diffInstance;
        }

        @Override
        public void run() {
            System.out.println("TestInstance2 is running......"+diffInstance);
            diffInstance.instance2();
        }

        @Override
        public String toString() {
            return "Instance2Syn{" +
                    "diffInstance=" + diffInstance +
                    '}';
        }
    }
    private synchronized void instance()
    {
        try {
            SleepTools.second(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("SyncInstance is going ...."+this.toString());
        try {
            SleepTools.second(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("SyncInstance is ended....."+this.toString());
    }
    private synchronized void instance2()
    {
        try {
            SleepTools.second(3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("SyncInstance2 is going ...."+this.toString());
        try {
            SleepTools.second(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("SyncInstance2 is ended....."+this.toString());
    }

    @Override
    public String toString() {

        return getClass().getName()+"@"+Integer.toBinaryString(hashCode());
    }

    public static void main(String[] args) {
        DiffInstance instance=new DiffInstance();
        Thread t=new Thread(new InstanceSync(instance));
        DiffInstance instance1=new DiffInstance();
        Thread t1=new Thread(new Instance2Syn(instance1));

        t.start();
        t1.start();

        try {
            SleepTools.second(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
