package cn.example.ch3;

import java.util.concurrent.atomic.AtomicStampedReference;

/**
 * ProjectName: jvm
 * packageName: cn.example.ch3
 * ClassName: UseAtomicStampedReference
 *
 * @author: 李朋飞
 * @time: 2021/12/25 20:02
 * 带版本戳的原子操作类
 **/
public class UseAtomicStampedReference {

    static AtomicStampedReference<String>asr=new AtomicStampedReference<>("makr",0);

    public static void main(String[] args) throws InterruptedException {
        final int oldstamp=asr.getStamp();
        final String oldreference=asr.getReference();
        System.out.println(oldreference+"========="+oldstamp);
        Thread rightStampThread=new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName()+"当前变量值 ：" +
                        oldreference+"当前版本戳："+oldstamp+"-"+asr.compareAndSet(oldreference,oldreference+"java",oldstamp,oldstamp+1));
            }
        });

        Thread erroThread=new Thread(new Runnable() {
            @Override
            public void run() {
                String reference=asr.getReference();
                System.out.println(Thread.currentThread().getName()+"-"+
                        asr.compareAndSet(reference,reference+"c",oldstamp,oldstamp+1));
            }
        });

        rightStampThread.start();
        rightStampThread.join();
        erroThread.start();
        erroThread.join();
        System.out.println(asr.getReference()+"=============="+asr.getStamp());
    }
}
