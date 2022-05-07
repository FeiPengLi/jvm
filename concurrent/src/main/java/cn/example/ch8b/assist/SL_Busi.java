package cn.example.ch8b.assist;

/**
 * ProjectName: jvm
 * packageName: cn.example.ch8b.assist
 * ClassName: SL_Busi
 *
 * @author: 李朋飞
 * @time: 2022/1/22 上午 10:35
 * <p>
 * 用sleep模拟实际的业务操作
 **/
public class SL_Busi {

    public static void buisness(int sleepTime) {
        try {
            Thread.sleep(sleepTime);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
