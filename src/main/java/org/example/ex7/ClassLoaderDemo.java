package org.example.ex7;

import sun.net.spi.nameservice.dns.DNSNameService;

/**
 * packageName:org.example.ex7
 * author:李朋飞
 * time:2021/12/4 11:51
 * ProjectName:jvm
 * ClassName: ClassLoaderDemo
 * 类加载器
 */
public class ClassLoaderDemo {

    public static void main(String[] args) {
        //启动类加载器
        System.out.println(String.class.getClassLoader());
        //扩展类加载器
        System.out.println(DNSNameService.class.getClassLoader());
        //应用类加载器
        System.out.println(ClassLoaderDemo.class.getClassLoader());
    }
}
