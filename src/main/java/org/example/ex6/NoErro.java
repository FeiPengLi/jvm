package org.example.ex6;

/**
 * packageName:org.example.ex6
 * author:李朋飞
 * time:2021/12/4 11:29
 * ProjectName:jvm
 * ClassName: NoErro
 * 加了finally 为啥不会抛出异常:查看字节码。一目了然
 */
public class NoErro {

    public static void main(String[] args) {
        NoErro noErro=new NoErro();
        System.out.println(noErro.read());
    }

    private int read() {
        try {
            int a=13/0;
            return a;
        }catch (Exception e){
//            e.printStackTrace();
            return 1;
        }finally {
            return 1;
        }
    }
}
