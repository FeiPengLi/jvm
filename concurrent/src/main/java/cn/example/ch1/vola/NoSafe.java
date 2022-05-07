package cn.example.ch1.vola;

/**
 * ProjectName: jvm
 * packageName: cn.example.ch1.vola
 * ClassName: NoSafe
 *
 * @author: 李朋飞
 * @time: 2021/12/12 17:47
 **/
public class NoSafe {

    //只可保证可见性，无法保证原子性
    private volatile long count=0;

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }

    //count进行累加,加锁保证原子性
    public synchronized  void inCount(){count++;}

    private static class Count extends Thread{
        private NoSafe simplOper;

        public Count(NoSafe simplOper) {
            this.simplOper = simplOper;
        }

        @Override
        public void run() {
            for (int i = 0; i < 10000; i++) {
                simplOper.inCount();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        NoSafe simpOper=new NoSafe();
        Count count1=new Count(simpOper);
        Count count2=new Count(simpOper);
        count1.start();
        count2.start();
        Thread.sleep(100);
        System.out.println(simpOper.count);
    }
}
