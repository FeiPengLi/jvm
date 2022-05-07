package org.example.ex9.stream.test;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * ProjectName: jvm
 * packageName: org.example.ex9.stream.test
 * ClassName: SerialStreamTest
 *
 * @author: 李朋飞
 * @time: 2021/12/4 20:45
 *
 * stream 串型迭代
 **/
public class SerialStreamTest {

    public static void SerialStreamForIntTest(int[]arr){
        long timeStart=System.currentTimeMillis();
        Arrays.stream(arr).min().getAsInt();
        long timeEnd=System.currentTimeMillis();
        System.out.println("SerialStream 比较int最小值 花费时间："+(timeEnd-timeStart));
    }

    public static void SerialStreamForObjectTest(List<Student> studentList){
        long timeStart=System.currentTimeMillis();
        Map<String , List<Student>> list=studentList.stream()
                .filter(student -> student.getHeight()>160)
                .collect(Collectors.groupingBy(Student::getSex));
        long timeEnd=System.currentTimeMillis();
        System.out.println("Stream分组后："+list);
        System.out.println("Stream串型花费时间："+(timeEnd-timeStart));
    }
}
