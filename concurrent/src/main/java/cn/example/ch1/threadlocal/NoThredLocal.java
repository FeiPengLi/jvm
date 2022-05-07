package cn.example.ch1.threadlocal;

/**
 * ProjectName: jvm
 * packageName: cn.example.ch1.threadlocal
 * ClassName: NoThredLocal
 *
 * @author: 李朋飞
 * @time: 2021/12/12 16:51
 * 不使用ThreadLocal
 **/
public class NoThredLocal {
    static Integer count=new Integer(1);

    public void StartThreadArray(){
        Thread[] runs=new Thread[3];
        for (int i = 0; i < runs.length; i++) {
            runs[i]=new Thread(new TestTask(i));
        }
        for (int i = 0; i < runs.length; i++) {
            runs[i].start();

        }
    }

    private class TestTask implements Runnable {
        int id;

        public TestTask(int id) {
            this.id=id;
        }

        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName()+":start");
            count=count+id;
            System.out.println(Thread.currentThread().getName()+":"+count);
        }
    }
    //
    public static void main(String[] args) {
        NoThredLocal noThredLocal=new NoThredLocal();
        noThredLocal.StartThreadArray();
    }
}
