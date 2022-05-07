package cn.example.ch6.schd;

import cn.example.tools.SleepTools;

import java.util.Date;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * ProjectName: jvm
 * packageName: cn.example.ch6.schd
 * ClassName: ScheduledCase
 *
 * @author: 李朋飞
 * @time: 2022/1/15 上午 11:06
 * 类说明：演示ScheduledThreadPoolExecutor的用法
 **/
public class ScheduledCase {
    public static void main(String[] args) {
        ScheduledThreadPoolExecutor schedule = new ScheduledThreadPoolExecutor(1);
        //延迟任务：仅执行一次
        schedule.schedule(new Runnable() {
            @Override
            public void run() {
                System.out.println("the task only run once!");
            }
        }, 3000, TimeUnit.MILLISECONDS);

        //固定延时时间间隔执行的任务
        schedule.scheduleWithFixedDelay(new Runnable() {
            @Override
            public void run() {
                System.out.println("*****fixDelay start,"+ScheduleWorker.formater.format(new Date()));
                try {
                    SleepTools.second(2);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("******fixDelay end,"+
                        ScheduleWorker.formater.format(new Date()));
            }
        }, 1000, 3000, TimeUnit.MILLISECONDS);

        //固定时间间隔执行的任务，理论上第二次任务再6000ms后执行，第三次再6000*2ms后执行
        schedule.scheduleAtFixedRate(new ScheduleWorkerTime(), 0,
                6000, TimeUnit.MILLISECONDS);

        //固定时间间隔执行的任务，开始执行触发异常，next周期将不会执行
        schedule.scheduleAtFixedRate(new ScheduleWorker(ScheduleWorker.HasException),
                0, 3000, TimeUnit.MILLISECONDS);

        //固定时间间隔执行的任务，虽然抛出异常，但被捕捉，next周期继续运行；
        //之前启明星在线监管定时拉去上报zip包。可以用这个实现
        schedule.scheduleAtFixedRate(new ScheduleWorker(ScheduleWorker.ProcessException),
                0, 3000, TimeUnit.MILLISECONDS);
    }
}
