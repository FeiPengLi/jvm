package cn.example.ch5.bq;

import java.util.concurrent.DelayQueue;

/**
 * ProjectName: jvm
 * packageName: cn.example.ch5.bq
 * ClassName: Test
 *
 * @author: 李朋飞
 * @time: 2022/1/3 16:14
 * 延迟队列测试程序
 **/
public class Test {
    public static void main(String[] args) throws InterruptedException {
        DelayQueue<ItemVo<Order>>queue=new DelayQueue<>();
        new Thread(new PutOrder(queue)).start();
        new Thread(new FetchOrder(queue)).start();

        for (int i = 0; i < 15; i++) {
            Thread.sleep(500);
            System.out.println(i*500);
        }
    }
}
