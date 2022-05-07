package cn.example.ch5.bq;

import java.util.concurrent.DelayQueue;

/**
 * ProjectName: jvm
 * packageName: cn.example.ch5.bq
 * ClassName: FetchOrder
 *
 * @author: 李朋飞
 * @time: 2022/1/3 16:14
 **/
public class FetchOrder implements Runnable{
    private DelayQueue<ItemVo<Order>>  queue;

    public FetchOrder(DelayQueue<ItemVo<Order>> queue) {
        this.queue = queue;
    }

    @Override
    public void run() {

        while(true){
            try {
                ItemVo<Order>item=queue.take();
                Order order=item.getData();
                System.out.println("get from queue:"+"data="+
                        order.getOrderNo()+","+order.getOrderMoney());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
