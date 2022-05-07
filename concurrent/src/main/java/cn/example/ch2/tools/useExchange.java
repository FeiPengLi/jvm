package cn.example.ch2.tools;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.Exchanger;

/**
 * ProjectName: jvm
 * packageName: cn.example.ch2.tools
 * ClassName: useExchange
 *
 * @author: 李朋飞
 * @time: 2021/12/25 16:46
 * exchage用法
 **/
public class useExchange {
    private static final Exchanger<Set<String> >exchange=new Exchanger<>();

    public static void main(String[] args) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                Set<String >setA=new HashSet<>();
                try {
                    setA.add("11");
                    System.out.println("setA:"+setA.stream().findFirst());
                    /*添加数据
                     * set.add(.....)
                     * */
                    setA=exchange.exchange(setA);
                    /*处理交换后的数据*/
                    System.out.println("setA:"+setA.stream().findFirst());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                Set<String >setB=new HashSet<>();
                try {
                    setB.add("22");
                    System.out.println("setB:"+setB.stream().findFirst());
                    /*添加数据
                     * set.add(.....)
                     * set.add(.....)
                     * */
                    setB= exchange.exchange(setB);
                    /*处理交换后的数据*/
                    System.out.println("setB:"+setB.stream().findFirst());
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }


}
