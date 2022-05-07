package cn.example.ch5.bq;

import java.util.concurrent.DelayQueue;

/**
 * ProjectName: jvm
 * packageName: cn.example.ch5.bq
 * ClassName: PutOrder
 *
 * @author: 李朋飞
 * @time: 2022/1/3 16:14
 *
 * 将订单推入队列
 **/
public class PutOrder implements Runnable{

    private DelayQueue<ItemVo<Order>>queue;

    public PutOrder(DelayQueue<ItemVo<Order>> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {
        Order orderTb=new Order("Tb12345",366);
        //5s到期
        ItemVo<Order>itemTb=new ItemVo<>(5,orderTb);
        queue.offer(itemTb);
        System.out.println("订单5s后超时："+orderTb.getOrderNo()+";"+orderTb.getOrderMoney());
        //8s后到期
        Order orderJd=new Order("jd54321",366);
        ItemVo<Order>itemJd=new ItemVo<>(8,orderJd);
        queue.offer(itemJd);
        System.out.println("订单8s超时："+orderJd.getOrderMoney()+";"+orderJd.getOrderNo());
    }
}
