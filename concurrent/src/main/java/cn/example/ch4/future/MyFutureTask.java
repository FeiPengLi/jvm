package cn.example.ch4.future;

import java.util.concurrent.*;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;

/**
 * ProjectName: jvm
 * packageName: cn.example.ch4.future
 * ClassName: MyFutureTask
 *
 * @author: 李朋飞
 * @time: 2022/1/3 11:31
 * FutureTask的get方法实现：
 * 1、允许多个线程get这个结果
 * 2、多个线程get这个结果时，可能任务还没运行完。
 * 3、任务运行完成后才能拿到结果，而且这个时候要让get结果的多个线程都可以拿到结果
 *
 **/
public class MyFutureTask<V> implements Runnable, Future<V> {

    private final Sync sync;

    public MyFutureTask(Callable<V>callable) {
        if (callable==null)throw new NullPointerException();
        this.sync = new Sync(callable);
    }

    private final class Sync extends AbstractQueuedSynchronizer{
        private static final int RUNNING=1;//任务正在执行
        private static final int RUN=2;//任务执行完毕
        private V result;//执行结果
        private Callable<V> callable;

        public Sync(Callable<V> callable) {
            super();
            this.callable = callable;
        }
        //任务没完成，让get结果的 线程全部进入同步队列，acquireshared
        //返回了说明可以拿结果了，
        V innerGet(){
            acquireShared(0);
            return result;
        }

        void innerSet(V v){
            for (;;){
                int s=getState();//获取执行状态
                if (s==RUN)
                    return;//任务执行完毕，退出；
                //尝试将任务状态设置为执行完成
                if (compareAndSetState(s,RUN)){
                    result=v;//设置执行结果
                    releaseShared(0);//释放控制权
                    return;
                }
            }
        }
        @Override
        protected boolean tryReleaseShared(int arg) {
            return true;
        }
        //任务没完成，返回-1，让get结果线程全部进入同步队列
        //返回1可以让所有再同步队列上等待的线程一一去获取结果
        @Override
        protected int tryAcquireShared(int arg) {
            return this.getState()==RUN?1:-1;
        }

        void innerRun(){
            if (this.compareAndSetState(0,RUNNING)){
                if (this.getState()==RUNNING){//再检查一次，双重保障
                        try {
                            //将call（）方法执行结果赋值给sync的result
                            this.innerSet(this.callable.call());
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                }else {
//                    如果不等于running表示被取消或是异常，这时调用get的线程
                    this.releaseShared(0);
                }
            }
        }
    }
    @Override
    public void run() {
        this.sync.innerRun();
    }

    @Override
    public boolean cancel(boolean mayInterruptIfRunning) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean isCancelled() {
         throw new UnsupportedOperationException();
    }

    @Override
    public boolean isDone() {
         throw new UnsupportedOperationException();
    }

    @Override
    public V get() throws InterruptedException, ExecutionException {
        return this.sync.innerGet();
    }

    @Override
    public V get(long timeout, TimeUnit unit) throws InterruptedException, ExecutionException, TimeoutException {
        throw new UnsupportedOperationException();
    }
}
