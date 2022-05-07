package org.example.ex6;

/**
 * packageName:org.example.ex6
 * author:李朋飞
 * time:2021/12/4 11:43
 * ProjectName:jvm
 * ClassName: SynchronizedDemo
 */
public class SynchronizedDemo {
    //锁的是当前方法对应的对象
    synchronized void m1(){
        System.out.println("m1()");
    }
    //静态方法修改的锁，锁的是当前方法对应的类对象为锁对象
    static synchronized void m2(){
        System.out.println("m2()");
    }
    final Object lock =new Object();
    void dolock(){
        //表明锁对象为该对象：即lock对象
        synchronized (lock){
            System.out.println("lock");
        }
    }
}
