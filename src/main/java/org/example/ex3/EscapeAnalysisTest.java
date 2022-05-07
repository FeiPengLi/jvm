package org.example.ex3;

/**
 * packageName:org.example.ex3
 * author:李朋飞
 * time:2021/11/30 22:21
 * ProjectName:jvm
 * ClassName: EscapeAnalysisTest
 * 逃逸分析 栈上分配
 * -XX:-DoEscapeAnalysis -XX:+PrintGCDetails
 * 添加以上vm参数则堆上分配，时间长
 */
public class EscapeAnalysisTest {
    public static void main(String[] args) throws InterruptedException {
        long start =System.currentTimeMillis();
        for (int i=0;i<5000000;i++){
            allocate();
        }
        System.out.println(System.currentTimeMillis()-start+" ms");
        Thread.sleep(50000);
    }
    static void allocate(){
        //myObject 引用没有出去，没有其他的地方使用，栈上分配
        MyObject myObject=new MyObject(2021,2021.5);
    }
    static class MyObject{
        int a;
        double b;
        MyObject(int a,double b){
            this.a=a;
            this.b=b;
        }
    }
}
