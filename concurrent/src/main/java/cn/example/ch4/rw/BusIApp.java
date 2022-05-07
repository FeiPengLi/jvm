package cn.example.ch4.rw;

import cn.example.tools.SleepTools;

import java.security.SecureRandom;

/**
 * ProjectName: jvm
 * packageName: cn.example.ch4.rw
 * ClassName: BusIApp
 *
 * @author: 李朋飞
 * @time: 2022/1/3 12:46
 * 对商品进行业务的应用
 **/
public class BusIApp {
    static final int readWriteRatio=10;//读写线程比例
    static final int minThreadCount=3;//最少线程数
    //读操作
    private static class GetThread implements Runnable{
        private GoodService goodService;

        public GetThread(GoodService goodService) {
            this.goodService = goodService;
        }

        @Override
        public void run() {
            long start=System.currentTimeMillis();
            for (int i = 0; i < 100; i++) {
                goodService.getNum();
            }
            System.out.println(Thread.currentThread().getName()+"读取商品耗时："+(System.currentTimeMillis()-start)+"ms");
        }
    }

    private static class SetThread implements Runnable{

        private GoodService goodService;

        public SetThread(GoodService goodService) {
            this.goodService = goodService;
        }

        @Override
        public void run() {
            long start=System.currentTimeMillis();
            SecureRandom r=new SecureRandom();
            for (int i = 0; i < 10; i++) {
                try {
                    SleepTools.ms(50);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                goodService.setNum(r.nextInt(10));
            }
            System.out.println(Thread.currentThread().getName()+"写商品耗时："+(System.currentTimeMillis()-start)+"ms");

        }
    }

    public static void main(String[] args) {
        GoodsInfo goodsInfo=new GoodsInfo("cup",10000,10000);
        GoodService goodService=new UseRwLock(goodsInfo);
        for (int i = 0; i < minThreadCount; i++) {
            Thread setT=new Thread(new SetThread(goodService));
            for (int j = 0; j < readWriteRatio; j++) {
                Thread getT=new Thread(new GetThread(goodService));
                getT.start();

            }
            try {
                SleepTools.ms(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            setT.start();
        }
    }
}
