package org.example.ex2;

/**
 * packageName:org.example.ex2
 * author:李朋飞
 * time:2021/11/30 21:56
 * ProjectName:jvm
 * ClassName: JVMObject
 */
public class JVMObject {
    public final static String MAN_TYPE="man";//常量
    public static  String WOMAN_TYPE="woman";//静态常量

    public static void main(String[] args) throws InterruptedException {
        Teacher t1=new Teacher();
        t1.setName("Mark");
        t1.setSexType(MAN_TYPE);
        t1.setAge(36);
        for (int i=0;i<15;i++){
            System.gc();;//主动触发gc
        }
        Teacher t2=new Teacher();
        t2.setName("King");
        t2.setSexType(MAN_TYPE);
        t2.setAge(16);
        Thread.sleep(Integer.MAX_VALUE);
    }

}
 class Teacher{
    String name;
    String sexType;
    int age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSexType() {
        return sexType;
    }

    public void setSexType(String sexType) {
        this.sexType = sexType;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
