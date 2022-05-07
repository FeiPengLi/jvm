package org.example.ex7.init;

/**
 * packageName:org.example.ex7.init
 * author:李朋飞
 * time:2021/12/4 14:47
 * ProjectName:jvm
 * ClassName: Initalization
 * 各种初始化场景
 * 通过vm参数可以观察操作是否辉导致子类的加载： -XX:+TraceClassLoading
 */
public class Initalization {

    public static void main(String[] args) {
        Initalization initalization=new Initalization();
//        initalization.M1();//打印子类的静态字段
//        initalization.M2();//使用数组方式创建
//        initalization.M3();//打印一个常量
        initalization.M4();//使用一个常量引用另外一个常量
    }
    public void M1(){
//        superclazz static init
//        123
//        只会触发父类的初始化，而不会触发子类的初始化（但是子类会被加载）
        System.out.println(SubClazz.value);
    }
    public void M2(){
        //使用数组方式，不会触发初始化（触发父类加载，不会触发子类加载）
        SuperClazz[] sca=new SuperClazz[10];
    }
    public void M3(){
        //打印常量，不会触发初始化（不会触发类加载，编译时常量已进入自己的class常量池）
        System.out.println(SuperClazz.HELLWORLD);
    }

    public void M4(){
//        superclazz static init
//        123
        // 只会触发父类的初始化，而不会触发子类的初始化（但是子类会被加载）
        System.out.println(SubClazz.WHAT);
    }



}
