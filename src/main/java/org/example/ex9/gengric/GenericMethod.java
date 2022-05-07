package org.example.ex9.gengric;

/**
 * ProjectName: jvm
 * packageName: org.example.ex9.gengric
 * ClassName: GenericMethod
 *
 * @author: 李朋飞
 * @time: 2021/12/4 18:09
 **/
public class GenericMethod {
    //泛型方法
    public <T> T genericMethod(T t){
        return t;
    }
    public void test(int x,int y){
        System.out.println(x+y);
    }

    public static void main(String[] args) {
        GenericMethod genericMethod=new GenericMethod();
        genericMethod.test(13,6);
    }
}
