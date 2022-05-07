package cn.example.ch6.comps;

import java.util.Random;
import java.util.concurrent.Callable;

/**
 * ProjectName: jvm
 * packageName: cn.example.ch6.comps
 * ClassName: WorkTask
 *
 * @author: 李朋飞
 * @time: 2022/1/14 下午 08:49
 **/
public class WorkTask implements Callable<Integer> {

    private String name;

    public WorkTask(String name) {
        this.name = name;
    }

    @Override
    public Integer call() throws Exception {
        int sleepTime=new Random().nextInt(1000);
        try {
            Thread.sleep(sleepTime);
        }catch (InterruptedException e){
            e.printStackTrace();
        }
        //返回给调用者值
        return sleepTime;
    }
}
