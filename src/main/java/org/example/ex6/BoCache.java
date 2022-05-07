package org.example.ex6;

/**
 * packageName:org.example.ex6
 * author:李朋飞
 * time:2021/12/4 11:24
 * ProjectName:jvm
 * ClassName: BoCache
 * IntegerCache 及修改
 * 自动拆箱装箱
 * -XX:AutoBoxCacheMax=256
 */
public class BoCache {
    public static void main(String[] args) {
        Integer i1=123;
        Integer i2=123;
        Integer i3=256;
        Integer i4=256;
        System.out.println(i1==i2);
        System.out.println(i3==i4);
//        true
//        false
//        加入jvm参数：-XX:AutoBoxCacheMax=256
//        true
//        true
    }
}
