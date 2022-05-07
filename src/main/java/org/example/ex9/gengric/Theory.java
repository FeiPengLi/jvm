package org.example.ex9.gengric;

import java.util.HashMap;
import java.util.Map;

/**
 * ProjectName: jvm
 * packageName: org.example.ex9.gengric
 * ClassName: Theory
 *
 * @author: 李朋飞
 * @time: 2021/12/4 18:29
 *
 * 泛型擦除
 **/
public class Theory {
    public static void main(String[] args) {
        Map<String,String> map=new HashMap<>();
        map.put("king","11");
        System.out.println(map.get("king"));//看反编译 为：System.out.println((String)map.get("king"));
    }
}
