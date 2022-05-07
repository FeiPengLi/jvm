package org.example.ex9.stream;

import org.example.ex9.stream.test.Student;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * ProjectName: jvm
 * packageName: org.example.ex9.stream
 * ClassName: StuWithStream
 *
 * @author: 李朋飞
 * @time: 2021/12/4 20:02
 * Stream使用
 **/
public class StuWithStream {
    public static void main(String[] args) {
        List<Student> stu = DataInit();
        groupBy(stu);
        filter(stu);
        total(stu);
        MaxAndMin(stu);
    }

    public static List<Student> DataInit() {
        List<Student> students = Arrays.asList(
                new Student("小明", 168, "男"),
                new Student("大明", 182, "男"),
                new Student("小白", 174, "男"),
                new Student("小黑", 186, "男"),
                new Student("小红", 156, "女"),
                new Student("小黄", 158, "女"),
                new Student("小青", 165, "女"),
                new Student("小紫", 172, "女")
        );
        return students;
    }

    //Stream分组
    public static void groupBy(List<Student> students) {
        Map<String, List<Student>> groupBy = students.stream().collect(Collectors.groupingBy(Student::getSex));
        System.out.println("分组后；" + groupBy);
    }

    //Stream实现过滤
    public static void filter(List<Student>students){
        List<Student> filter=students.stream().
                filter(student -> student.getHeight()>180)
                .collect(Collectors.toList());
        System.out.println("过滤后："+filter);
    }
    //Stream实现求和
    public static void total(List<Student> students){
        int stu=students.stream()
                .mapToInt(Student::getHeight)
                .sum();
        System.out.println(stu);
    }

    //Stream求最大和最小
    public static void MaxAndMin(List<Student> studentList){
        int maxHeight=studentList
                .stream()
                .mapToInt(Student::getHeight)
                .max()
                .getAsInt();
        System.out.println("stu Max:"+maxHeight);
        int min=studentList
                .stream()
                .mapToInt(Student::getHeight)
                .min()
                .getAsInt();
        System.out.println(" stu Min:"+min);
    }
}
