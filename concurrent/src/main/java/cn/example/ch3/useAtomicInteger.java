package cn.example.ch3;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * ProjectName: jvm
 * packageName: cn.example.ch3
 * ClassName: useAtomicInteger
 *
 * @author: 李朋飞
 * @time: 2021/12/25 19:12
 * Integer基本原子操作类
 **/
public class useAtomicInteger {
    static AtomicInteger ai=new AtomicInteger(10);

    public static void main(String[] args) {
        System.out.println(ai.getAndIncrement());//10
        System.out.println(ai.getAndDecrement());//11
        System.out.println(ai.incrementAndGet());//11
        System.out.println(ai.addAndGet(31));//42
        System.out.println(ai.getAndAdd(48));//42
        System.out.println(ai.get());//90
    }

}
