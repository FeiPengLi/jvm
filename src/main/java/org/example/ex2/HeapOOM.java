package org.example.ex2;

/**
 * packageName:org.example.ex2
 * author:李朋飞
 * time:2021/11/30 21:18
 * ProjectName:jvm
 * ClassName: HeapOOM
 * vm args: -Xms30M -Xmx30M -XX:+PrintGCDetails
 * Java heap space
 */
public class HeapOOM {
    public static void main(String[] args) {
        String [] strings=new String[35*1024*1024];//35兆数组（堆）
    }
}
