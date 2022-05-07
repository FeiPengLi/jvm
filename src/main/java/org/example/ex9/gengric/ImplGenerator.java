package org.example.ex9.gengric;

/**
 * ProjectName: jvm
 * packageName: org.example.ex9.gengric
 * ClassName: ImplGenerator
 *
 * @author: 李朋飞
 * @time: 2021/12/4 18:12
 **/
public class ImplGenerator<T> implements Generator<T>{
    private T data;

    @Override
    public T next() {
        return data;
    }

    public ImplGenerator(T data){this.data=data;}

    public static void main(String[] args) {
        ImplGenerator<String>implGenerator=new ImplGenerator<>("King");
        System.out.println(implGenerator.next());
    }
}
