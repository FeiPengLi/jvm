package cn.example.ch8a.vo;

import cn.example.ch8a.CheckJobProcesser;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * ProjectName: jvm
 * packageName: cn.example.ch8a.vo
 * ClassName: JobInfo
 *
 * @author: 李朋飞
 * @time: 2022/1/16 下午 12:11
 *
 * 提交给框架的工作实体类
 * 表示本批次需要处理的同性质任务的一个集合，task
 **/
public class JobInfo<R> {
    private final String jobName;//工作名，用以区分框架中的唯一工作
    private final int jobLength;//工作中任务的长度
    private final ITtaskProcessor<?,?>taskprocessor;//处理工作中任务的处理器；
    private AtomicInteger successCount;//任务成功的次数
    private AtomicInteger taskProcessCount;//工作中任务目前已经处理的次数

    //存放每个任务的处理结果，共查询使用
    private LinkedBlockingDeque<TaskResult<R>> taskDetailQueues;

    private final long expireTime;//保留的工作的结果信息供查询的时长

    private static CheckJobProcesser checkJob=CheckJobProcesser.getInstance();

    public JobInfo(String jobName, int jobLength,
                   ITtaskProcessor<?, ?> taskprocessor,
                   long expireTime) {
        this.jobName = jobName;
        this.jobLength = jobLength;
        this.taskprocessor = taskprocessor;
        this.successCount =new AtomicInteger(0);
        this.taskProcessCount = new AtomicInteger(0);
        this.taskDetailQueues = new LinkedBlockingDeque<>(jobLength);
        this.expireTime = expireTime;
    }

    public int getJobLength() {
        return jobLength;
    }

    public ITtaskProcessor<?, ?> getTaskprocessor() {
        return taskprocessor;
    }
    //提供工作中失败的次数
    public int getFailCount(){return taskProcessCount.get()-successCount.get();}

    public int getSuccessCount() {
        return successCount.get();
    }

    public int getTaskProcessCount() {
        return taskProcessCount.get();
    }
    //提供工作的整体进度信息
    public String getTotalProcess(){
        return "failed["+getFailCount()+"]sucess["+successCount.get()+"]/Current["+taskProcessCount.get()
                +"]Total["+jobLength+"]";
    }

    //提供工作中每个任务的处理结果
    public List<TaskResult<R>> getTaskDetail(){
        List<TaskResult<R>>taskResultList=new LinkedList<>();
        TaskResult<R>taskResult;
        while((taskResult=taskDetailQueues.pollFirst())!=null){
            taskResultList.add(taskResult);
        }
        return taskResultList;
    }

    /**
     * 每个任务处理完成后，记录任务的处理结果，因为从业务应用的角度来说
     * 对查询任务进度数据的一致性要求不高
     * 保证最终一致性即可，无需对整个方法加锁
     * **/
    public void addTaskResult(TaskResult<R>taskResult){
        if (TaskResultType.Success.equals(taskResult.getResultType())){
            successCount.incrementAndGet();
        }
        taskProcessCount.incrementAndGet();
        taskDetailQueues.addLast(taskResult);
        if (taskProcessCount.get()==jobLength){
            checkJob.putJob(jobName,expireTime);
        }
    }
}
