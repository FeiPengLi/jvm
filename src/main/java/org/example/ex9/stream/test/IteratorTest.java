package org.example.ex9.stream.test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * ProjectName: jvm
 * packageName: org.example.ex9.stream.test
 * ClassName: IteratorTest
 *
 * @author: 李朋飞
 * @time: 2021/12/4 20:44
 **/
public class IteratorTest {
    public static void IteratorForIntTest(int[] arr){
        long timeStart=System.currentTimeMillis();
        int min =Integer.MAX_VALUE;
        for (int i = 0; i <arr.length ; i++) {
            if (arr[i]<min){
                min=arr[i];
            }
        }
        long timeEnd=System.currentTimeMillis();
        System.out.println(" Iterator 比较int最小值 花费时间："+(timeEnd-timeStart));
    }
    public static void IteratorForObjectTest(List<Student> studentList){
        long timeStar=System.currentTimeMillis();
        Map<String ,List<Student>>stuMap=new HashMap<>();
        for (Student stu:studentList
             ) {
            if (stu.getHeight()>160){
                if (stuMap.get(stu.getSex())==null){
                    List<Student>list=new ArrayList<>();
                    list.add(stu);
                    stuMap.put(stu.getSex(),list);
                }else
                    stuMap.get(stu.getSex()).add(stu);
            }
        }
        long timeEnd = System.currentTimeMillis();
        System.out.println("Iterator花费时间："+(timeEnd-timeStar));
    }
}
