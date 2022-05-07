package org.example.ex6;

/**
 * packageName:org.example.ex6
 * author:李朋飞
 * time:2021/12/4 11:18
 * ProjectName:jvm
 * ClassName: AnnotationDemo
 * 自定义注解
 */
@KingAnnotation
public class AnnotationDemo {
    @KingAnnotation
    public void test(@KingAnnotation int a){}
}
