package cn.example.ch1.base;

/**
 * ProjectName: jvm
 * packageName: cn.example.ch1.base
 * ClassName: NewThreadMethod
 *
 * @author: 李朋飞
 * @time: 2021/12/11 10:42
 * 启动线程的方式
 **/
public class NewThreadMethod {

    /**
     * 扩展至Thread类
     */
    static class UseThread extends Thread{
        @Override
        public void run() {
            super.run();
            System.out.println("I am extends Thread ");
        }
    }

    /**
     * 实现Runnable接口
     */

    static class UseRunnable implements Runnable{
        @Override
        public void run() {
            System.out.println("i am implements Runnable");
        }
    }

    public static void main(String[] args) {
        UseThread useThread=new UseThread();
        useThread.start();
//        useThread.start();//调用两次会报错：java.lang.IllegalThreadStateException
        UseRunnable useRunnable=new UseRunnable();
        new Thread(useRunnable).start();

    }
}
