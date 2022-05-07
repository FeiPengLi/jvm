package cn.example.ch4;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * ProjectName: jvm
 * packageName: cn.example.ch4
 * ClassName: LockCase
 *
 * @author: 李朋飞
 * @time: 2021/12/26 10:43
 * 使用lock的范例
 **/
public class LockCase {
    private Lock lock=new ReentrantLock();
    private int age =100000;

    private static class TextThread extends  Thread{
        private LockCase lockCase;

        public TextThread(LockCase target, String name) {
            super(name);
            this.lockCase = target;
        }

        @Override
        public void run() {
            for (int i = 0; i < 100000; i++) {

                lockCase.test();
                System.out.println(Thread.currentThread().getName()+" age ="+lockCase.getAge());
            }
        }
    }

    public void test(){
        lock.lock();
        try{
            age++;
        }finally {
            lock.unlock();
        }
    }

    public void test2(){
        lock.lock();
        try{
            age--;
        }finally {
            lock.unlock();
        }
    }

    public int getAge() {
        return age;
    }

    public static void main(String[] args) {
        LockCase lockCase=new LockCase();
        Thread endThread=new TextThread(lockCase,"endThread");
        endThread.start();
        for (int i = 0; i < 10000; i++) {
            lockCase.test2();
        }
        System.out.println(Thread.currentThread().getName()+" age ="+lockCase.getAge());
    }
}
