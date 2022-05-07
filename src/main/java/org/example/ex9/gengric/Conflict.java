package org.example.ex9.gengric;

import java.util.List;

/**
 * ProjectName: jvm
 * packageName: org.example.ex9.gengric
 * ClassName: Conflict
 *
 * @author: 李朋飞
 * @time: 2021/12/4 17:37
 * 泛型的注意事项
 * jdk的编译器可以通过，idea不行。方法特征：返回类型 + 方法名 + param
 **/
public class Conflict {

//    public static String method(List<String> stringList){
//        System.out.println("List");
//        return "ok";
//    }
    public static Integer method(List<Integer> integerList){
        System.out.println("list");
        return 0;
    }

    public static void main(String[] args) {
        System.out.println("abc");
        String cde = "cde";
        System.out.println("abc" + cde);
        String c = "abc".substring(2,3);
        String d = cde.substring(2);
        System.out.println(c);
        System.out.println(d);
    }
}
