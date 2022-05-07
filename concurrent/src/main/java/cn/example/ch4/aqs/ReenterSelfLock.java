package cn.example.ch4.aqs;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * ProjectName: jvm
 * packageName: cn.example.ch4.aqs
 * ClassName: ReenterSelfLock
 *
 * @author: 李朋飞
 * @time: 2021/12/26 11:01
 *
 * 实现自己的可重入独占锁
 **/
public class ReenterSelfLock implements Lock {
    //将操作代理到sync类即可
    private final Sync sync=new Sync();
    //静态内部类，自定义同步器
    private static class Sync extends AbstractQueuedSynchronizer {
        //是否处于占用状态
        @Override
        protected boolean isHeldExclusively() {
            return getState()>0;
        }

        @Override
        protected boolean tryAcquire(int acquires) {
            if (compareAndSetState(0,1)){
                setExclusiveOwnerThread(Thread.currentThread());
                return true;
            }else if(getExclusiveOwnerThread()==Thread.currentThread()){
                setState(getState()+1);//可重入
                return true;
            }
            return false;
        }

        @Override
        protected boolean tryRelease(int arg) {
            if (getExclusiveOwnerThread()!=Thread.currentThread()){
                throw new IllegalMonitorStateException();
            }
            if (getState()==0){
                throw new IllegalMonitorStateException();
            }
            setState(getState()-1);
            if (getState()==0)
                setExclusiveOwnerThread(null);
            return true;
        }

        Condition newCondition(){return new ConditionObject();}
    }


    @Override
    public void lock() {
        System.out.println(Thread.currentThread().getName()+" ready get lock");
        sync.acquire(1);
        System.out.println(Thread.currentThread().getName()+" already got lock");
    }

    @Override
    public void lockInterruptibly() throws InterruptedException {
        sync.acquireInterruptibly(1);
    }

    @Override
    public boolean tryLock() {
        return sync.tryAcquire(1);
    }

    @Override
    public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
        return sync.tryAcquireNanos(1, unit.toNanos(time));
    }

    @Override
    public void unlock() {
        System.out.println(Thread.currentThread().getName()+" ready release lock");
        sync.release(1);
        System.out.println(Thread.currentThread().getName()+" already released lock");
    }

    @Override
    public Condition newCondition() {
        return sync.newCondition();
    }
    public boolean isLocked() {
        return sync.isHeldExclusively();
    }

}
