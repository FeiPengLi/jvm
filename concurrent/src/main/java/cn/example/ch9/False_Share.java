package cn.example.ch9;

/**
 * ProjectName: jvm
 * packageName: cn.example.ch9
 * ClassName: False_Share
 *
 * @author: 李朋飞
 * @time: 2022/1/22 下午 09:32
 *
 * 伪共享
 **/
public class False_Share implements Runnable    {


    public final static int NUM_THREADS=Runtime.getRuntime().availableProcessors();

    public final static long ITERATIONS=500000000L;
    private final int arrayIndex;

    private static VolatileLongAnno[]longs=new VolatileLongAnno[NUM_THREADS];

    static {
        for (int i = 0; i < longs.length; i++) {
            longs[i]=new VolatileLongAnno();
        }
    }
    public False_Share(int arrayIndex) {
        this.arrayIndex = arrayIndex;
    }

    public static void main(String[] args) throws InterruptedException {
        final long start=System.nanoTime();
        runTest();
        System.out.println("duration="+(System.nanoTime()-start));
    }
    private static void runTest() throws InterruptedException {
        Thread[]threads=new Thread[NUM_THREADS];
        for (int i = 0; i < threads.length; i++) {
            threads[i]=new Thread(new False_Share(i));

        }
        for (Thread t :
                threads) {
            t.start();
        }

        for (Thread t :
                threads) {
            t.join();
        }
    }
    @Override
    public void run() {
        long i=ITERATIONS+1;
        while(0!=--i){
            longs[arrayIndex].value=i;
        }
    }

    public final static class VolatileLong {
        public volatile long value = 0L;
    }

    // long padding避免false sharing
    // 按理说jdk7以后long padding应该被优化掉了，但是从测试结果看padding仍然起作用
    public final static class VolatileLongPadding {
        public long p1, p2, p3, p4, p5, p6, p7;
        public volatile long value = 0L;
        volatile long q0, q1, q2, q3, q4, q5, q6;
    }


    /**
     * jdk8新特性，Contended注解避免false sharing
     * Restricted on user classpath
     * Unlock: -XX:-RestrictContended
     */
    @sun.misc.Contended
    public final static class VolatileLongAnno{
        public volatile long value=0L;
    }
}
