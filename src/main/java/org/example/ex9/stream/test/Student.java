package org.example.ex9.stream.test;

/**
 * ProjectName: jvm
 * packageName: org.example.ex9.stream.test
 * ClassName: Student
 *
 * @author: 李朋飞
 * @time: 2021/12/4 20:04
 **/
public class Student {

    private String name;
    private int height;
    private String sex;

    public Student(String name, int height, String sex) {
        this.name = name;
        this.height = height;
        this.sex = sex;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }
}
