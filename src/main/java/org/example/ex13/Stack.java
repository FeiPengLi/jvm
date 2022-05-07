package org.example.ex13;

/**
 * ProjectName: jvm
 * packageName: org.example.ex13
 * ClassName: Statck
 *
 * @author: 李朋飞
 * @time: 2021/12/4 22:14
 * 手写栈
 **/
public class Stack {
    public Object[] elements;//数组来保存
    private int size =0;
    private static final int Cap = 200000;

    public Stack() {
        elements = new Object[Cap];
    }

    public void push(Object e){ //入栈
        elements[size] = e;
        size++;
    }
    public Object pop(){  //出栈
        size = size -1;
        Object o = elements[size];
        elements[size] =null; //不用---引用干掉，GC可以正常回收次对象
        return o;
    }
}
