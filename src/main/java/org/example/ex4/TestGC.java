package org.example.ex4;

import java.util.LinkedList;
import java.util.List;

/**
 * packageName:org.example.ex4
 * author:李朋飞
 * time:2021/12/4 11:02
 * ProjectName:jvm
 * ClassName: TestGC
 * vm:
 * -XX:+PrintGCDetails -XX:+UseSerialGC 单线程gc
 * -XX:+PrintGCDetails
 * -XX:+PrintGCDetails -XX:+UseConcMarkSweepGC
 * -XX:+PrintGCDetails -XX:+UseG1GC
 */
public class TestGC {
    /**
     * 不停的往list中填充数据，不断的触发gc
     *
     */
    public static class FillListThread extends Thread{
        List<Object> list=new LinkedList<>();

        @Override
        public void run() {
            try {
                while(true){
                    if (list.size()*512/1024/1024>=990){
                        list.clear();
                        System.out.println(" list is clear");
                    }
                    byte[]bl;
                    for (int i=0;i<100;i++) {
                        bl=new byte[512];
                        list.add(bl);

                    }
                }
            }catch (Exception e){}
        }
    }


    public static class TimeThread extends  Thread{
        public final static long startTime=System.currentTimeMillis();

        @Override
        public void run() {
            try {
                while(true){
                    long t=System.currentTimeMillis()-startTime;
                    Thread.sleep(100);
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


    public static void main(String[] args) {
        FillListThread myThread=new FillListThread();
        TimeThread timeThread=new TimeThread();
        myThread.start();
        timeThread.start();
    }
}
