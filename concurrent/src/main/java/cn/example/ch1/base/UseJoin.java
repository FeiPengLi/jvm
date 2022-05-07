package cn.example.ch1.base;

import cn.example.tools.SleepTools;

/**
 * ProjectName: jvm
 * packageName: cn.example.ch1
 * ClassName: UseJoin
 *
 * @author: 李朋飞
 * @time: 2021/12/10 22:23
 * join方法的使用
 **/
public class UseJoin {

    protected static class Goddess implements Runnable{
        Thread thread;
        public Goddess(Thread thread){
            this.thread=thread;
        }
        public Goddess(){}

        @Override
        public void run() {
            System.out.println("Goddess开始排队买票。。。。。。");
            if (thread!=null) {
                try {
                    thread.join();
                } catch (InterruptedException e) {
                }
                try {
                    SleepTools.second(2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName()+"Goddess买票完成。。。。。");
            }
        }

    }
    static  class GoddessBoyfriend implements Runnable{
        private Thread thread;

        public GoddessBoyfriend() {
        }

        public GoddessBoyfriend(Thread thread) {
            this.thread = thread;
        }

        @Override
        public void run() {
            try {
                SleepTools.second(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println("GoddessBoyfriend开始排队买票。。。。。");
            System.out.println(Thread.currentThread().getName()+"GoddessBoyfriend买票完成。。。。。。");
        }
    }

    public static void main(String[] args) throws InterruptedException {
       Thread lison=Thread.currentThread();
       GoddessBoyfriend goddessBoyfriend=new GoddessBoyfriend();
       Thread gbf=new Thread(goddessBoyfriend);
       Goddess goddess=new Goddess(gbf);
       Thread g=new Thread(goddess);
       g.start();
       gbf.start();
        System.out.println("lison开始排队买票。。。。。。");
        g.join();
        SleepTools.second(2);
        System.out.println(Thread.currentThread().getName()+"lison排队买票完成。。。。。。");
    }
}
