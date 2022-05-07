package cn.example.ch6.comps;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * ProjectName: jvm
 * packageName: cn.example.ch6.comps
 * ClassName: CompletionCase
 *
 * @author: 李朋飞
 * @time: 2022/1/14 下午 09:19
 **/
public class CompletionCase {
    private final int POOL_SIZE=Runtime.getRuntime().availableProcessors();
    private final int TOTAL_TASK=Runtime.getRuntime().availableProcessors()*10;

    //自己写集合来实现获取线程池中任务的返回结果；
    public void testQueue() throws InterruptedException, ExecutionException {
        long start=System.currentTimeMillis();
        AtomicInteger count=new AtomicInteger(0);
        //创建线程池,固定数线程池
        ExecutorService pool= Executors.newFixedThreadPool(POOL_SIZE);
        //队列，拿任务的执行结果
        BlockingQueue<Future<Integer>>  queue=new LinkedBlockingQueue<>();
        //向队列里面扔任务
        for (int i = 0; i < TOTAL_TASK; i++) {
            Future<Integer> future=pool.submit(new WorkTask("Executask-"+i));
            queue.add(future);

        }
        //检查线程池子任务执行结果
        for (int i = 0; i < TOTAL_TASK; i++) {
            int sleptTime=queue.take().get();
            count.addAndGet(sleptTime);
        }

        //关闭线程池，任务执行结束
        pool.shutdown();
        System.out.println("------------------tasks sleep time "+count.get()+
                "ms,and spend time  "+(System.currentTimeMillis()-start)+"ms");
    }

    //使用Completion
    public void testCompletion() throws InterruptedException, ExecutionException {
        long start=System.currentTimeMillis();
        AtomicInteger count=new AtomicInteger(0);
        ExecutorService pool=Executors.newFixedThreadPool(POOL_SIZE);
        CompletionService<Integer> cservice=new ExecutorCompletionService<>(pool);

        //向 里面扔任务
        for (int i = 0; i < TOTAL_TASK; i++) {
            cservice.submit(new WorkTask("execTask="+i));

        }
        //检查线程池任务执行结果
        for (int i = 0; i < TOTAL_TASK; i++) {
            int sleptTime=cservice.take().get();
            count.addAndGet(sleptTime);
        }
        //关闭线程池
        pool.shutdown();
        System.out.println("==========task sleep time "+count.get()+"ms,and spend time "+
                (System.currentTimeMillis()-start)+"ms");
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CompletionCase completionCase=new CompletionCase();
        completionCase.testQueue();
        completionCase.testCompletion();
    }
}
