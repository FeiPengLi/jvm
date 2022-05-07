package cn.example.ch8a.demo;

import cn.example.ch8a.vo.ITtaskProcessor;
import cn.example.ch8a.vo.TaskResult;
import cn.example.ch8a.vo.TaskResultType;
import cn.example.tools.SleepTools;

import java.util.Random;

/**
 * ProjectName: jvm
 * packageName: cn.example.ch8a.demo
 * ClassName: Mytask
 *
 * @author: 李朋飞
 * @time: 2022/1/16 下午 12:09
 *
 * 一个实际任务类，将数值加上一个随机数，并休眠随机时间
 **/
public class Mytask implements ITtaskProcessor<Integer,Integer> {
    @Override
    public TaskResult<Integer> taskExecute(Integer data) {
        Random r=new Random();
        int flag=r.nextInt(500);
        try {
            SleepTools.ms(flag);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if (flag<=300){
            Integer returnValue=data.intValue()+flag;
            return new TaskResult<>(TaskResultType.Success,returnValue);
        }else if (flag>301&&flag<=400){
            return new TaskResult<>(TaskResultType.Failure,-1,"Failure");
        }else {
            try {
                throw new RuntimeException("异常发生！！！");
            }catch (Exception e){
                return new TaskResult<>(TaskResultType.Exception,-1,e.getMessage()) ;
            }
        }
    }
}
