package cn.example.tools;

import java.util.concurrent.TimeUnit;

/**
 * ProjectName: jvm
 * packageName: cn.example.tools
 * ClassName: SleepTools
 *
 * @author: 李朋飞
 * @time: 2021/12/10 22:34
 * 线程休眠辅助工具类
 **/
public class SleepTools {

    /**
     * 按秒休眠
     * @param seconds
     * @throws InterruptedException
     */
    public static final void second(int seconds) throws InterruptedException {
        TimeUnit.SECONDS.sleep(seconds);
    }

    /**
     * 按毫秒休眠
     * @param seconds
     * @throws InterruptedException
     */
    public static final void ms(int seconds) throws InterruptedException {
        TimeUnit.MILLISECONDS.sleep(seconds);
    }


    public static void main(String[] args) {
        ErrorCode.MAN.username();
    }
}
