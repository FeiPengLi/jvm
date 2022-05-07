package cn.example.ch8a.demo;

import cn.example.ch8a.PendingJobPool;
import cn.example.ch8a.vo.TaskResult;
import cn.example.tools.SleepTools;

import java.util.List;
import java.util.Random;

/**
 * ProjectName: jvm
 * packageName: cn.example.ch8a.demo
 * ClassName: Apptest
 *
 * @author: 李朋飞
 * @time: 2022/1/16 下午 12:09
 *
 * 模拟一个应用程序，提交工作和任务，并查询任务进度
 **/
public class Apptest {
    private final static String JOB_NAME="计算数值";
    private final static int JOB_LENGTH=1000;

    //查询任务进度的线程
    private static class QueryResult implements Runnable{
        private PendingJobPool pool;

        public QueryResult(PendingJobPool pool) {
            super();
            this.pool = pool;
        }

        @Override
        public void run() {
            int i=0;
            while(i<350){
                List<TaskResult<String>>taskDetail=pool.getTaskDetail(JOB_NAME);
                if (!taskDetail.isEmpty()){
                    System.out.println(pool.getTaskProgess(JOB_NAME));
                    System.out.println(taskDetail);
                }
                try {
                    SleepTools.ms(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                i++;
            }
        }
    }

    public static void main(String[] args) {
        Mytask mytask=new Mytask();
        PendingJobPool pool=PendingJobPool.getInstance();
        pool.registerJob(JOB_NAME,JOB_LENGTH,mytask,5);
        Random r=new Random();
        for (int i = 0; i <JOB_LENGTH; i++) {
            pool.putTask(JOB_NAME,r.nextInt(1000));
        }
        Thread t=new Thread(new QueryResult(pool));
        t.start();
    }
}
