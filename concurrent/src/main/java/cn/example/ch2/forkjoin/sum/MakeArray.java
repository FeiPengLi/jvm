package cn.example.ch2.forkjoin.sum;

import java.util.Random;

/**
 * ProjectName: jvm
 * packageName: cn.example.ch2.forkjoin.sum
 * ClassName: MakeArray
 *
 * @author: 李朋飞
 * @time: 2021/12/25 13:19
 **/
public class MakeArray {
    public static final int ARRAY_LENGTH=4000;

    public final static int THRESHOLD=47;

    public static  int[]makeArray(){
        Random r=new Random();
        int[]result=new  int[ARRAY_LENGTH];
        for (int i=0;i<ARRAY_LENGTH;i++){
            result[i]=r.nextInt(ARRAY_LENGTH*3);
        }

        return result;
    }
}
