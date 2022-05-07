package org.example.ex9.stream;

import java.util.*;

/**
 * ProjectName: jvm
 * packageName: org.example.ex9.stream
 * ClassName: StreamDemo
 *
 * @author: 李朋飞
 * @time: 2021/12/4 19:47
 * Stream 使用入门
 **/
public class StreamDemo {

    public static void main(String[] args) {
        List<String> names= Arrays.asList("张三", "李四", "王老五", "李三", "刘老四", "王小二", "张四", "张五六七");
        List ll=new ArrayList();
        for (String name: names){
            if (name.startsWith("张")){
                ll.add(name.length());
            }
        }

        int maxLeanz= (int) Collections.max(ll);
        System.out.println(maxLeanz);
        int maxLeng=names.stream().filter(name->name.startsWith("张")).mapToInt(String::length).max().getAsInt();
        System.out.println(maxLeng);
    }


}
