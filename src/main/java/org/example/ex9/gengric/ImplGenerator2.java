package org.example.ex9.gengric;

/**
 * ProjectName: jvm
 * packageName: org.example.ex9.gengric
 * ClassName: ImplGenerator
 *
 * @author: 李朋飞
 * @time: 2021/12/4 18:12\
 * 泛型实现方式2
 **/
public class ImplGenerator2 implements Generator<String>{

    @Override
    public String next() {
        return "King";
    }


    public static void main(String[] args) {
        ImplGenerator2 implGenerator=new ImplGenerator2();
        System.out.println(implGenerator.next());
    }
}
