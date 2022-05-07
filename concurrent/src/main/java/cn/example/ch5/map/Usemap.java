package cn.example.ch5.map;

import java.util.concurrent.ConcurrentHashMap;

/**
 * ProjectName: jvm
 * packageName: cn.example.ch5.map
 * ClassName: Usemap
 *
 * @author: 李朋飞
 * @time: 2022/1/3 18:10
 * ConcurrentHashMap使用
 **/
public class Usemap {
    public static void main(String[] args) {
        ConcurrentHashMap<KeyVo,String>map=new ConcurrentHashMap<>();
        KeyVo keyVo=new KeyVo(1,"A");
        System.out.println("put不存在的值。。。");
        System.out.println(map.put(keyVo,"AA"));
        System.out.println(map.get(keyVo));

        System.out.println("put已存在的key...");
        System.out.println(map.put(keyVo,"BB"));
        System.out.println(map.get(keyVo));

        System.out.println("putifabsent已存在的key");
        System.out.println(map.putIfAbsent(keyVo,"CC"));
        System.out.println(map.get(keyVo));

        System.out.println("putifabsent不存在的key。。。。。");
        KeyVo keyVo1=new KeyVo(2,"b");
        System.out.println(map.putIfAbsent(keyVo1,"cc"));
        System.out.println(map.get(keyVo1));

    }
}
