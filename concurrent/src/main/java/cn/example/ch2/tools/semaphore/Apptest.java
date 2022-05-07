package cn.example.ch2.tools.semaphore;

import cn.example.tools.SleepTools;

import java.sql.Connection;
import java.util.Random;

/**
 * ProjectName: jvm
 * packageName: cn.example.ch2.tools.semaphore
 * ClassName: Apptest
 *
 * @author: 李朋飞
 * @time: 2021/12/25 16:33
 **/
public class Apptest {
    private static DBPoolSemaphore dbPool=new DBPoolSemaphore();

    private static class BusiThread extends Thread{

        @Override
        public void run() {
            Random r=new Random();
            long start=System.currentTimeMillis();
            try {
                Connection connection=dbPool.takeConnect();
                System.out.println("Thread_"+Thread.currentThread().getId()
                +"_获取数据库连接共耗时【"+(System.currentTimeMillis()-start)+"ms】");
                SleepTools.ms(100+r.nextInt(100));
                System.out.println("查询完成！归还连接！");
                dbPool.returnConnect(connection);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < 50; i++) {
            Thread thread=new BusiThread();
            thread.start();
        }
    }
}
