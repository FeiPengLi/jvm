package org.example.ex7.init;

/**
 * packageName:org.example.ex7.init
 * author:李朋飞
 * time:2021/12/4 14:49
 * ProjectName:jvm
 * ClassName: SuperClazz
 */
public class SuperClazz {
    static {
        System.out.println("superclazz static init ");
    }
    {
        System.out.println("superclazz 代码块 init");
    }
    {
        System.out.println("superclazz 代码块2 init");
    }
    public SuperClazz(){
        System.out.println("superclazz 无参构造 init");
    }

    public static int value=123;
    public static final int WHAT=value;
    public static final String HELLWORLD="hello king";
}
