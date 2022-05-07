package org.example.ex7;

/**
 * packageName:org.example.ex7
 * author:李朋飞
 * time:2021/12/4 14:41
 * ProjectName:jvm
 * ClassName: SpiDemo
 */
public class SpiDemo {
    static{
        System.out.println("初始化static");
    }
    public SpiDemo(){
        System.out.println("asdf");
    }
    {
        System.out.println("代码亏啊");
    }
    public static void main(String[] args) {
        new SpiDemo();
        try {
            Class.forName("com.mysql.jdbc.Driver");
//            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


    }
}
