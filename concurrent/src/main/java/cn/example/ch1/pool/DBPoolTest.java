package cn.example.ch1.pool;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * ProjectName: jvm
 * packageName: cn.example.ch1.pool
 * ClassName: DBPoolTest
 *
 * @author: 李朋飞
 * @time: 2021/12/11 12:28
 * 数据库连接获取等待超时模式
 **/
public class DBPoolTest {
    static DBPool pool=new DBPool(10);
    //控制器：控制main线程将会等待所有的Worker结束后才能继续执行
    static CountDownLatch end;

    public static void main(String[] args) throws InterruptedException {
        int threadCount=50;//线程数量
        end=new CountDownLatch(threadCount);
        int count=20;//每个线程操作次数
        AtomicInteger got=new AtomicInteger();//计数器：统计拿到连接的线程
        AtomicInteger notGot=new AtomicInteger();//计数器：统计没有拿到连接的线程

        for (int i = 0; i < threadCount; i++) {
            Thread thread=new Thread(new Worker(count,got,notGot));
            thread.start();
        }
        end.await();
        System.out.println("总共尝试了："+threadCount*count+"次");
        System.out.println("拿到连接的次数："+got);
        System.out.println("未拿到的次数："+notGot);
    }

    static class Worker implements Runnable{
        int count;
        AtomicInteger got;
        AtomicInteger notGot;

        public Worker(int count, AtomicInteger got, AtomicInteger notGot) {
            this.count = count;
            this.got = got;
            this.notGot = notGot;
        }

        @Override
        public void run() {
            while (count>0){
                try {
                    Connection connection=pool.fetchConnection(1000);
                    if (connection!=null){
                        try {
                            connection.createStatement();
                            connection.commit();
                        } finally {
                            pool.releaseConnection(connection);
                            got.incrementAndGet();
                        }
                    }else{
                        notGot.incrementAndGet();
                        System.out.println(Thread .currentThread().getName()+" 等待超时！");
                    }
                } catch (InterruptedException | SQLException e) {
                    e.printStackTrace();
                }finally {
                    count--;
                }
            }
            end.countDown();
        }
    }
}
