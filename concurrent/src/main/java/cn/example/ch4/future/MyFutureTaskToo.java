package cn.example.ch4.future;

import java.util.concurrent.*;

/**
 * ProjectName: jvm
 * packageName: cn.example.ch4.future
 * ClassName: MyFutureTaskToo
 *
 * @author: 李朋飞
 * @time: 2022/1/3 12:09
 *
 * 类说明：FutureTask的get方法实现：
 **/
public class MyFutureTaskToo<V> implements Runnable, Future<V> {

    Callable<V>callable;//封装业务逻辑
    V result=null;

    public MyFutureTaskToo(Callable<V> callable) {
        this.callable = callable;
    }

    @Override
    public void run() {
        try {
            result=callable.call();
        } catch (Exception e) {
            e.printStackTrace();
        }
        synchronized (this){
            System.out.println("--------");
            this.notifyAll();
        }
    }

    @Override
    public boolean cancel(boolean mayInterruptIfRunning) {
        return false;
    }

    @Override
    public boolean isCancelled() {
        return false;
    }

    @Override
    public boolean isDone() {
        return false;
    }

    @Override
    public V get() throws InterruptedException, ExecutionException {
       if (result!=null)
           return result;
        System.out.println("等待执行结果，，，");
        synchronized (this){
            this.wait();
        }
        return result;
    }

    @Override
    public V get(long timeout, TimeUnit unit) throws InterruptedException, ExecutionException, TimeoutException {
        return null;
    }
}
