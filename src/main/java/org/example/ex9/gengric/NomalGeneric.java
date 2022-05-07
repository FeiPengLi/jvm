package org.example.ex9.gengric;

/**
 * ProjectName: jvm
 * packageName: org.example.ex9.gengric
 * ClassName: NomalGeneric
 *
 * @author: 李朋飞
 * @time: 2021/12/4 18:25
 *
 * 普通泛型类
 **/
public class NomalGeneric<T> {
    private T data;

    public NomalGeneric(){}
    public NomalGeneric(T data){this();this.data=data;}

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public static void main(String[] args) {
        NomalGeneric<Object> nomalGeneric = new NomalGeneric<>();
        nomalGeneric.setData("King");
        System.out.println(nomalGeneric.getData());
    }
}
