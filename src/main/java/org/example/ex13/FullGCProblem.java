package org.example.ex13;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * ProjectName: jvm
 * packageName: org.example.ex13
 * ClassName: FulllGCProblem
 *
 * @author: 李朋飞
 * @time: 2021/12/10 21:10
 *
 * vm: -XX:+PrintGC -Xms200M -Xmx200M
 * -XX:+HeapDumpOnOutOfMemoryError
 **/
public class FullGCProblem {

    private static ScheduledThreadPoolExecutor executor = new ScheduledThreadPoolExecutor(50,
            new ThreadPoolExecutor.DiscardOldestPolicy());

    public static void main(String[] args) throws InterruptedException {
        executor.setMaximumPoolSize(50);
        while (true){
            calc();
            Thread.sleep(100);
        }
    }

    //多线程计算
    /**
     *
     */
    private static void calc() {
        List<UserInfo>taskList=getAllCardInfo();
        taskList.forEach(userInfo -> {
            executor.scheduleWithFixedDelay(()->{
                userInfo.user();
            },2,3, TimeUnit.SECONDS);
        });
    }

    private static List<UserInfo> getAllCardInfo(){
        List<UserInfo> taskList=new ArrayList<>();
        for (int i=0;i<100;i++){
            UserInfo userInfo=new UserInfo();
            taskList.add(userInfo);

        }
        return taskList;

    }

    private static class UserInfo{
        String name="king";
        int age=18;

        BigDecimal money=new BigDecimal(999.999);
        public void user(){
            System.out.println("调用到我了");
        }

    }


}
