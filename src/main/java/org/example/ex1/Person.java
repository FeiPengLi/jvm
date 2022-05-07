package org.example.ex1;

/**
 * packageName:org.example.ex1
 * author:李朋飞
 * time:2021/11/28 18:20
 * ProjectName:jvm
 * ClassName: Person
 * 栈帧执行对内存区域的影响
 */
public class Person {

    public int work()throws Exception{

        int x=1;
        int y=2;
        int z=(x+y)*10;
        return z;
    }

    public static void main(String[] args) throws Exception {
        Person person=new Person();//Person 栈中。 new Person 对象是在堆
        int res=person.work();
        System.out.println("结果:"+res);
    }

}
