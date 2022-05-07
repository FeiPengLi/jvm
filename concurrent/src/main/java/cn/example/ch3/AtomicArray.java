package cn.example.ch3;

import java.util.concurrent.atomic.AtomicIntegerArray;

/**
 * ProjectName: jvm
 * packageName: cn.example.ch3
 * ClassName: AtomicArray
 *
 * @author: 李朋飞
 * @time: 2021/12/25 19:59
 * 原子操作数组类型
 **/
public class AtomicArray {
    static int[] value=new int[]{1,2};
    static AtomicIntegerArray ai=new AtomicIntegerArray(value);

    public static void main(String[] args) {
        ai.getAndSet(0,3);
        System.out.println(ai.get(0));
        System.out.println(value[0]);
    }
}
