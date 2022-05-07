package org.example.ex8;

/**
 * packageName:org.example.ex8
 * author:李朋飞
 * time:2021/12/4 16:17
 * ProjectName:jvm
 * ClassName: I
 */
public interface I {
    default void infm(){
        System.out.println("接口default 方法");
    }
    void inf();
}
