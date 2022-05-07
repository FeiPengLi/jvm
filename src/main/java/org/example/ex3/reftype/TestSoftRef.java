package org.example.ex3.reftype;

import java.lang.ref.SoftReference;
import java.util.LinkedList;
import java.util.List;

/**
 * packageName:org.example.ex3.reftype
 * author:李朋飞
 * time:2021/12/4 10:36
 * ProjectName:jvm
 * ClassName: TestSoftRef
 * 软引用：当内存不够用抛出异常时回收，手动触发gc不会回收，因为内存未满，未用完
 * -Xms20M -Xmx20M
 */
public class TestSoftRef {
    public static class User{
        public  int id =0;
        public String name="";
        public User(int id,String name){
            super();
            this.id=id;
            this.name=name;

        }

        @Override
        public String toString() {
            return "User{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    '}';
        }
    }

    public static void main(String[] args) {
        User u=new User(1,"lipengfei");
        SoftReference userSoftReference=new SoftReference<>(u);
        u=null;//干掉强引用，确保只有 userSoftReference
        System.out.println(userSoftReference.get());
        System.gc();
        System.out.println(" After gc");
        System.out.println(userSoftReference.get());
        List<byte[]> list=new LinkedList<>();
        try {
            for (int i=0;i<100;i++){
                list.add(new byte[1*1024*1024]);//共100m
            }
        }catch (Throwable throwable){
            throwable.getMessage();
            System.out.println("***********************errror:"+userSoftReference.get());
        }
    }
}
