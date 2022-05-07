package org.example.ex9.stream.test;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * ProjectName: jvm
 * packageName: org.example.ex9.stream.test
 * ClassName: ParallelStreamTest
 *
 * @author: 李朋飞
 * @time: 2021/12/4 20:45
 * stream并行迭代
 **/
public class ParallelStreamTest {

    public static void ParallelStreamForIntTest(int[]arr){
        long timeStart=System.currentTimeMillis();
        Arrays.stream(arr).parallel().min().getAsInt();
        long timeEnd=System.currentTimeMillis();
        System.out.println(" ParallelStream 比较int最小值 花费时间："+(timeEnd-timeStart));

    }

    public static void ParallelStreamForObjectTest(List<Student> studentList){
        long timeStart=System.currentTimeMillis();
        Map<String,List<Student>> stumap=studentList.parallelStream()
                .filter(student -> student.getHeight()>160)
                .collect(Collectors.groupingBy(Student::getSex));
        long timeEnd=System.currentTimeMillis();
        System.out.println(" ParaelStream并行花费的时间："+(timeEnd-timeStart));
    }
}
