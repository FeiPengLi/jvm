package org.example.ex9.stream.test;

import java.util.ArrayList;
import java.util.List;

/**
 * ProjectName: jvm
 * packageName: org.example.ex9.stream.test
 * ClassName: PStream
 *
 * @author: 李朋飞
 * @time: 2021/12/4 20:45
 *
 * 测试parallelStream线程池线程数为 cpu核心数
 **/
public class PStream {

    public static void main(String[] args) throws InterruptedException {
        List<Integer> list=new ArrayList<>(100);
        for (int i = 0; i < 100; i++) {
            list.add(1);
        }
        //parallelStream 都是使用的一个fork-Join线程池，线程数为cpu核心数
        //可通过一下配置修改
//        System.setProperty("java.util.concurrent.ForkJoinPool.common.parallelism","2");
        for (int i = 0; i <= 50; i++) {
            new Thread("test-"+i){
                String currThreadName=this.getName();

                @Override
                public void run() {
                    list.parallelStream()
                            .forEach(n->{
                                Thread c=Thread.currentThread();
                                System.out.println(currThreadName+"==========>"
                                +c.getClass().getName()+":"+c.getName()+":"+c.getId());
                                try {
                                    Thread.sleep(10);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                            });
                }
            }.start();
        }
        Thread.sleep(10000);
    }
}
