package org.example.ex7.init;

/**
 * packageName:org.example.ex7.init
 * author:李朋飞
 * time:2021/12/4 14:48
 * ProjectName:jvm
 * ClassName: SubClazz
 */
public class SubClazz extends SuperClazz{
    static {
        System.out.println("SubClazz static init ");
    }
    {
        System.out.println("SubClazz 代码块 init");
    }
    {
        System.out.println("SubClazz 代码块2 init");
    }
    public SubClazz(){
        System.out.println("SubClazz 无参构造 init");
    }
}
