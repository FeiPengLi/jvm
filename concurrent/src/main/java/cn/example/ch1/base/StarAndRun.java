package cn.example.ch1.base;

/**
 * ProjectName: jvm
 * packageName: cn.example.ch1.base
 * ClassName: StarAndRun
 *
 * @author: 李朋飞
 * @time: 2021/12/11 11:16
 * StarAndRun在执行上的区别
 **/
public class StarAndRun {
    public static class ThreadRun extends Thread{
        @Override
        public void run() {
            int i=10;
            while (i>0){
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("I am "+Thread.currentThread().getName()+" and now the i="+i);
                i--;
            }
        }
    }

    public static void main(String[] args) {
        ThreadRun run=new ThreadRun();
        run.setName("threadRun");
        run.start();
    }
}
