package cn.example.ch4.aqs;

import cn.example.tools.SleepTools;

import java.util.concurrent.locks.Lock;

/**
 * ProjectName: jvm
 * packageName: cn.example.ch4.aqs
 * ClassName: TestReenterSelfLock
 *
 * @author: 李朋飞
 * @time: 2021/12/26 16:53
 **/
public class TestReenterSelfLock {
    static final Lock lock=new ReenterSelfLock();
    public void reenter(int x){
        lock.lock();
        try {
            System.out.println(Thread.currentThread().getName()+":递归层级："+x);
            int y=x-1;
            if (y==0)return;
            else
                reenter(y);
        }finally {
            lock.unlock();
        }
    }

    public void test(){
        class Worker extends Thread{
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName());
                try {
                    SleepTools.second(1);
                    reenter(3);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        for (int i = 0; i < 3; i++) {
            Worker w=new Worker();
            w.start();
        }
    }

    public static void main(String[] args) {
        TestReenterSelfLock testReenterSelfLock=new TestReenterSelfLock();
        testReenterSelfLock.test();
    }
}
