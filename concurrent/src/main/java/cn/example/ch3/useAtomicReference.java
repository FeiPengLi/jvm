package cn.example.ch3;

import java.util.concurrent.atomic.AtomicReference;

/**
 * ProjectName: jvm
 * packageName: cn.example.ch3
 * ClassName: useAtomicReference
 *
 * @author: 李朋飞
 * @time: 2021/12/25 19:20
 * 引用类型的原子操作类
 **/
public class useAtomicReference {
    static AtomicReference<UserInfo> atomicReference;

    public static void main(String[] args) {
        UserInfo user=new UserInfo("Mark",10);
        atomicReference=new AtomicReference<>(user);
        UserInfo updateUser=new UserInfo("Bill",12);
        atomicReference.compareAndSet(user,updateUser);
        System.out.println(atomicReference.get()   );
        System.out.println(user);
    }

    static class UserInfo{
        private volatile String name;
        private int age;

        public UserInfo(String name, int age) {
            this.name = name;
            this.age = age;
        }

        public String getName() {
            return name;
        }

        public int getAge() {
            return age;
        }

        @Override
        public String toString() {
            return "UserInfo{" +
                    "name='" + name + '\'' +
                    ", age=" + age +
                    '}';
        }
    }
}
