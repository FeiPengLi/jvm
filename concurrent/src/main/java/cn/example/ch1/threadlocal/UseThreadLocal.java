package cn.example.ch1.threadlocal;

/**
 * ProjectName: jvm
 * packageName: cn.example.ch1.threadlocal
 * ClassName: UseThreadLocal
 *
 * @author: 李朋飞
 * @time: 2021/12/11 22:03
 *
 * ThreadLocal使用
 **/
public class UseThreadLocal {

    private static ThreadLocal<Integer>intLocal=new ThreadLocal<Integer>(){
        @Override
        protected Integer initialValue() {
            return 1;
        }
    };

    public void StartThreadArray(){
        Thread[] runs=new Thread[3];
        for (int i = 0; i < runs.length; i++) {
            runs[i]=new Thread(new TestThread(i));
        }
        for (int i = 0; i < runs.length; i++) {
            runs[i].start();

        }
    }
    //线程的工作是将ThradLocal变量的值变化，并写回，看线程之间是否会相互影响
    public static class TestThread implements Runnable{

        int id;

        public TestThread(int id) {
            this.id = id;
        }

        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName()+":start");
            Integer s=intLocal.get();
            s=s+id;
            intLocal.set(s);
            System.out.println(Thread.currentThread().getName()+":"+intLocal.get());
        }
    }

    public static void main(String[] args) {
        UseThreadLocal useThreadLocal=new UseThreadLocal();
        useThreadLocal.StartThreadArray();
    }
}
