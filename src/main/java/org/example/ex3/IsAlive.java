package org.example.ex3;

/**
 * packageName:org.example.ex3
 * author:李朋飞
 * time:2021/12/4 10:19
 * ProjectName:jvm
 * ClassName: IsAlive
 * 判断对象的存活
 * vm: -XX:+PrintGC
 */
public class IsAlive {
    public Object instance=null;
    private byte[] bigsize=new byte[10*1024*1024];

    public static void main(String[] args) {
        IsAlive objectA=new IsAlive();
        IsAlive objectB=new IsAlive();
        //相互引用
        objectA.instance=objectB;
        objectB.instance=objectA;

        //切断可达
        objectA=null;
        objectB=null;
        System.gc();
    }
}
