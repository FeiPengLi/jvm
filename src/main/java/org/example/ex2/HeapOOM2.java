package org.example.ex2;

import java.util.LinkedList;
import java.util.List;

/**
 * packageName:org.example.ex2
 * author:李朋飞
 * time:2021/11/30 21:22
 * ProjectName:jvm
 * ClassName: HeapOOM2
 * -Xms3m -Xmx3m
 * -XX:+HeapDumpOnOutOfMemoryError (默认关闭)
 * gc调优分析jvm的分代收集
 */
public class HeapOOM2 {
    public static void main(String[] args) {
        List<Object> list=new LinkedList<>();//当前虚拟机中引用的对象（局部变量表）
        int i=0;
        while (true){
            i++;
            if (i%1000==0){
                try {
                    Thread.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                list.add(new Object());//不能回收，优先回收再抛出异常
            }
        }
    }
}
