package org.example.ex2;

import java.nio.ByteBuffer;

/**
 * packageName:org.example.ex2
 * author:李朋飞
 * time:2021/11/30 21:10
 * ProjectName:jvm
 * ClassName: DirectOOM
 * vm args : -XX:MaxDirectMemorySize=100m
 */
public class DirectOOM {
    public static void main(String[] args) {
        ByteBuffer b=ByteBuffer.allocateDirect(128*1024*1024);//直接内存申请128M
    }
}
