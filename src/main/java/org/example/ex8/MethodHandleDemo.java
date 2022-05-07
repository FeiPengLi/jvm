package org.example.ex8;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;

/**
 * ProjectName: jvm
 * packageName: org.example.ex8
 * ClassName: MethodHandleDemo
 * @desc: 方法句柄 MethodHandle 使用案例+
 * @author: 李朋飞
 * @time: 2021/12/4 16:44
 **/
public class MethodHandleDemo {
    static class Bike{
        String sound(){
            return " ding ding ding";
        }
    }

    static class Animal{
        String sound(){
            return " wow wow wow ";
        }
    }

    static class Man extends Animal{
        @Override
        String sound() {
            return " ha ha ha ";
        }
    }

    String sound(Object o) throws Throwable  {
        //方法句柄 工厂方法Factory
        MethodHandles.Lookup lookup=MethodHandles.lookup();
        //方法类型表示接受的参数和返回类型；第一个餐宿时返回参数
        MethodType methodType=MethodType.methodType(String.class);
        //拿到具体的methodHandle（findVirtual相当于字节码）
        MethodHandle methodHandle=lookup.findVirtual(o.getClass(),"sound",methodType);
        String obj= (String) methodHandle.invoke(o);
        return obj;
    }

    public static void main(String[] args) throws Throwable {
        String str=new MethodHandleDemo().sound(new Bike());
        System.out.println(str);
        str=new MethodHandleDemo().sound(new Animal());
        System.out.println(str);
        str= new MethodHandleDemo().sound(new Man());
        System.out.println(str);
    }
}
