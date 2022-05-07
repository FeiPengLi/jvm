package cn.example.ch1.synchronizeds;

/**
 * ProjectName: jvm
 * packageName: cn.example.ch1.synchronizeds
 * ClassName: SyncTest
 *
 * @author: 李朋飞
 * @time: 2021/12/11 21:18
 *
 * synchronized使用方法
 **/
public class SyncTest {


    private long count=0;
    private Object obj=new Object();//作为一个锁

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }

    //作用再同步块上
    public void inCount(){
        synchronized (obj){
            count++;
        }
    }
    //作用再方法上
    public synchronized void inCount2(){count++;}

    //作用再同步块上但是锁的是当前类的对象实例
    public void inCount3(){
        synchronized(this){
            count++;
        }
    }

    private static class Count extends Thread{
        private SyncTest syncTest;

        public Count(SyncTest syncTest) {
            this.syncTest = syncTest;
        }

        @Override
        public void run() {
            for (int i = 0; i < 1000; i++) {
                syncTest.inCount();

            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        SyncTest syncTest=new SyncTest();
        Count count=new Count(syncTest);
        Count count1=new Count(syncTest);
        count.start();
        count1.start();
        Thread.sleep(100);
        System.out.println(syncTest.count);
    }
}
