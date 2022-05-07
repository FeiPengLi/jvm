package cn.example.ch1.threadlocal;

import cn.example.tools.SleepTools;

/**
 * ProjectName: jvm
 * packageName: cn.example.ch1.threadlocal
 * ClassName: ThreadLocalUnsafeUse
 *
 * @author: 李朋飞
 * @time: 2021/12/12 17:15
 * ThreadLocal线程不安全使用
 **/
public class ThreadLocalUnsafeUse implements Runnable{

    public volatile   Number number=new Number(0) ;

    public static ThreadLocal<Number>value=new ThreadLocal<>();
    @Override
    public void run() {
        //每个线程计数加一
        number.setNum(number.getNum()+1);
        //放入ThreadLocal
        value.set(number);
        try {
            SleepTools.ms(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName()+"="+value.get().getNum());

    }


    public static void main(String[] args) {
        for (int i = 0; i < 5; i++) {
            new Thread(new ThreadLocalUnsafeUse()).start();
        }
    }
    private static class Number{
        private int num;
        public Number(int num){this.num=num;}

        public int getNum() {
            return num;
        }

        public void setNum(int num) {
            this.num = num;
        }

        @Override
        public String toString() {
            return "Number{" +
                    "num=" + num +
                    '}';
        }
    }
}
