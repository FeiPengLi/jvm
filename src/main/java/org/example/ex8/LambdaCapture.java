package org.example.ex8;

/**
 * packageName:org.example.ex8
 * author:李朋飞
 * time:2021/12/4 16:28
 * ProjectName:jvm
 * ClassName: LambdaCapture
 */
public class LambdaCapture {
    public static void main(String[] args) {
        repeatMessage();
        repeatMessage("捕获",5);
    }


    /**
     *
     * @param text
     * @param count
     */
    public static void repeatMessage(String text,int count){
        Runnable r=()->{
            for (int i=0;i<count;i++){
                System.out.println(text);
            }
        };

        new Thread(r).start();
    }

    public static void repeatMessage(){
        Runnable r=()-> {
            System.out.println("hello king!");
        };
        new Thread(r).start();
    }
}
