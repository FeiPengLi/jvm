package cn.example.ch1.threadlocal;

import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * ProjectName: jvm
 * packageName: cn.example.ch1.threadlocal
 * ClassName: ThreadLocalOOM
 *
 * @author: 李朋飞
 * @time: 2021/12/12 17:02
 *
 * ThreadLocal造成的内存泄露演示
 **/
public class ThreadLocalOOM {

    private static final int TASK_LOOP_SIZE=100;
    final static ThreadPoolExecutor poolExecutor=new ThreadPoolExecutor(
            5,5,1, TimeUnit.MINUTES
            ,new LinkedBlockingQueue<>()
    );

    static class LocalVariable{
        private byte[] a=new byte[1024*1024*5];//初始化一个5M大小的数组
    }
    ThreadLocal<LocalVariable> localVariableThreadLocal;

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < TASK_LOOP_SIZE; ++i) {
            poolExecutor.execute(new Runnable() {
                @Override
                public void run() {
                    ThreadLocalOOM oom=new ThreadLocalOOM();
                    oom.localVariableThreadLocal=new ThreadLocal<>();
                    oom.localVariableThreadLocal.set((new LocalVariable()));
                    System.out.println("use local varaiable");
//                    oom.localVariableThreadLocal.remove();
                }
            });

            Thread.sleep(100);
        }
        System.out.println("pool execute over.");
    }
}
