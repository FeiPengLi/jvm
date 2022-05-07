package cn.example.ch4.condition;

import cn.example.ch4.aqs.ReenterSelfLock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * ProjectName: jvm
 * packageName: cn.example.ch4.condition
 * ClassName: ExpressCond
 *
 * @author: 李朋飞
 * @time: 2022/1/3 11:11
 **/
public class ExpressCond {
    public final static String CITY="shanghai";
    private int km;
    private String site;

    private Lock kmlock=new ReentrantLock();
    private Lock siteLock=new ReentrantLock();
    private Condition kmCond=kmlock.newCondition();
    private Condition siteCond=siteLock.newCondition();

    public ExpressCond(){}

    public ExpressCond(int km,String site){
        this.km=km;
        this.site=site;

    }

    public void  changekm(){
        kmlock.lock();
        try {
            this.km=101;
            kmCond.signal();
        }finally {
            kmlock.unlock();
        }
    }
    public void changeSite(){
        siteLock.lock();
        try {
            this.site="beijing";
            siteCond.signal();

        }finally {
            siteLock.unlock();
        }
    }
    public void waitekm(){
        kmlock.lock();
        try {
            while(this.km<100){
                try {
                    kmCond.await();
                    System.out.println("Check site thread["+
                            Thread.currentThread().getName()+"]is be notified!");
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
        }finally {
            kmlock.unlock();
        }
        System.out.println("this km is "+this.km+",I will change db");
    }

    public void waiteSite(){
        siteLock.lock();
        try {
            while(this.site.equals(CITY)){
                try {
                    siteCond.await();
                    System.out.println("check site thread["+Thread.currentThread().getName()+"]is be notify");
                }catch (InterruptedException e){
                    e.printStackTrace();
                }
            }
        }finally {
            siteLock.unlock();
        }
        System.out.println("the site is "+this.site+",I will call user");
    }


}
