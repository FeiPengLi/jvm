package cn.example.ch8b.assist;

/**
 * ProjectName: jvm
 * packageName: cn.example.ch8b.assist
 * ClassName: Consts
 *
 * @author: 李朋飞
 * @time: 2022/1/21 下午 09:24
 * 系统常量类
 **/
public class Consts {
    //获取本机cpu数量
    public static final int THREAD_COUNT=
            Runtime.getRuntime().availableProcessors();

    //每个文档中题目的个数
    public static final int QUESTION_COUNT_IN_DOC=60;
    //题库大小
    public static final int SIZE_OF_QUESTION_BANK=2000;

}
