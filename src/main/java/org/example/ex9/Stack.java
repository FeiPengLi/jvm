package org.example.ex9;

import com.sun.tools.internal.xjc.reader.xmlschema.bindinfo.BIConversion;

/**
 * ProjectName: jvm
 * packageName: org.example.ex9
 * ClassName: Stack
 * 手写栈
 * @author: 李朋飞
 * @time: 2021/12/4 17:31
 **/
public class Stack {
    public Object[] elements;//数组保存
    private int size=0;
    private static final int cap=2000;

    public Stack(){
        elements=new Object[cap];
    }
    ///入栈
    public void push(Object o){
        elements[size]=o;
        size++;
    }
    public Object pop(){

        Object o=elements[size];
        elements[size]=null;
        size=size-1;
        return o;
    }
}
