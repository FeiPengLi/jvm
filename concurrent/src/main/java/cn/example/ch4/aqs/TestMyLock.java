package cn.example.ch4.aqs;

import cn.example.tools.SleepTools;

import java.util.concurrent.locks.Lock;

/**
 * ProjectName: jvm
 * packageName: cn.example.ch4.aqs
 * ClassName: TestMyLock
 *
 * @author: 李朋飞
 * @time: 2021/12/26 16:41
 **/
public class TestMyLock {
    public void test(){
        final Lock lock=new SelfLock();
        class Worker extends Thread{
            @Override
            public void run() {
                lock.lock();
                System.out.println(Thread.currentThread().getName());
                try {
                    SleepTools.second(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }finally {
                    lock.unlock();
                }
            }
        }
        for (int i = 0; i <4; i++) {
            Worker w=new Worker();
            w.start();
        }

        for (int i = 0; i < 10; i++) {
            try {
                SleepTools.second(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        TestMyLock testMyLock=new TestMyLock();
        testMyLock.test();
    }
}
