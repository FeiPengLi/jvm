package org.example.ex7;

import org.example.other.KingClassLoader;

/**
 * packageName:org.example.ex7
 * author:李朋飞
 * time:2021/12/4 12:20
 * ProjectName:jvm
 * ClassName: ClassLoaderCheck
 */
public class ClassLoaderCheck {
    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        KingClassLoader classLoader=new KingClassLoader("D:/study/jvm/target/classes/org/example/ex7/","ClassLoaderDemo");
        Class c=classLoader.loadClass("ClassLoaderDemo");
        System.out.println(c.getClassLoader());
        System.out.println(c.getClassLoader().getParent());
        System.out.println(c.getClassLoader().getParent().getParent());
        c.newInstance();
    }
}
