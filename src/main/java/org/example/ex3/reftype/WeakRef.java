package org.example.ex3.reftype;

import java.lang.ref.WeakReference;

/**
 * packageName:org.example.ex3.reftype
 * author:李朋飞
 * time:2021/12/4 10:55
 * ProjectName:jvm
 * ClassName: WeakRef
 * 弱引用 : 触发gc就会回收
 */
public class WeakRef {

    public static class User{
        public int id;
        public String name="lipengfei";

        public User(int id, String name) {
            this.id = id;
            this.name = name;
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
        User u=new User(1,"li");
        WeakReference<User>userWeakReference=new WeakReference<>(u);
        u=null;
        System.out.println(userWeakReference.get());
        System.gc();
        System.out.println("After gc");
        System.out.println(userWeakReference.get());
    }


}
