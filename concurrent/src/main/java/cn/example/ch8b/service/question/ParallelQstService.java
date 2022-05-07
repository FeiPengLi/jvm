package cn.example.ch8b.service.question;

import cn.example.ch8a.vo.TaskResult;
import cn.example.ch8b.assist.SL_QuestionBank;
import cn.example.ch8b.vo.QuestionInCacheVo;
import cn.example.ch8b.vo.QuestionInDBVo;
import cn.example.ch8b.vo.TaskResultVo;

import java.util.concurrent.*;

/**
 * ProjectName: jvm
 * packageName: cn.example.ch8b.service.question
 * ClassName: ParallelQstService
 *
 * @author: 李朋飞
 * @time: 2022/1/22 上午 09:52
 **/
public class ParallelQstService {
    //题目在本地的缓存
    private static ConcurrentHashMap<Integer , QuestionInCacheVo>questionCache=
            new ConcurrentHashMap<>();
    //正在处理的题目缓存
    private static ConcurrentHashMap<Integer, Future<QuestionInCacheVo>>

            processingQuestionCache=new ConcurrentHashMap<>();

    //处理题目的线程池
    private static ExecutorService makeQuestionExector=
            Executors.newCachedThreadPool();

    public static TaskResultVo makeQuestion(Integer questionId){
        QuestionInCacheVo questionInCacheVo=questionCache.get(questionId);
        if (null==questionInCacheVo){
            System.out.println("题目["+questionId +"]不存在，准备启动！");
            return new TaskResultVo(getQuestionFuture(questionId));
        }else{
            String questionSha= SL_QuestionBank.getQuestionSha(questionId);
            if (questionInCacheVo.getQuestionSha().equals(questionSha)){
                System.out.println("题目【"+questionId +"】在缓存中存在，可以使用");
                return new TaskResultVo(questionInCacheVo.getQuestionDetail());
            }else{
                System.out.println("题目【"+questionId +"在缓存中已过期，准备更新！");
                return new TaskResultVo(getQuestionFuture(questionId));
            }

        }
    }

    private static Future<QuestionInCacheVo> getQuestionFuture(Integer questionId) {
        Future<QuestionInCacheVo>questionFuture=processingQuestionCache.get(questionId);
        try {
            if (questionFuture==null){
                QuestionInDBVo questionInDBVo=SL_QuestionBank.getQuestion(questionId);
                QuestionTask questionTask=new QuestionTask(questionInDBVo,questionId);
//                不靠谱的做法，无法避免两个线程对同一个题目进行处理
//                questionFuture=makeQuestionExector.submit(questionTask);
//                processingQuestionCache.putIfAbsent(questionId,questionFuture);
//                如果直接改成
//                processingQuestionCache.putIfAbsent(questionId,questionFuture);
//                questionFuture = makeQuestionExector.submit(questionTask);
//                也不行，因为ConcurrentHashMap的value是不允许为null的，那么就需要
//                  另做处理
//                将任务包装成FutureTask，投入线程池执行和保存到缓存
                FutureTask<QuestionInCacheVo>ft=new FutureTask<>(questionTask);
                questionFuture=processingQuestionCache.putIfAbsent(questionId,ft);
                if (questionFuture==null){
                    questionFuture=ft;
                    makeQuestionExector.execute(ft);
                    System.out.println("当前任务已启动，请等待完成后");
                }else{
                    System.out.println("有其他线程开启了题目计算任务，本任务无需开启");
                }
            }else{
                System.out.println("当前已经有了题目的计算任务，不必重复开启");
            }
            return questionFuture;
        }catch (Exception e){
          processingQuestionCache.remove(questionId);
          e.printStackTrace();
          throw e;
        }
    }

    private static class QuestionTask implements Callable<QuestionInCacheVo>{

        QuestionInDBVo questionInDBVo;
        Integer questionId;

        public QuestionTask(QuestionInDBVo questionInDBVo, Integer questionId) {
            this.questionInDBVo = questionInDBVo;
            this.questionId = questionId;
        }

        @Override
        public QuestionInCacheVo call() throws Exception {
            try {
                String questionDetail = QstService.makeQuestion(questionId,questionInDBVo.getDetail());
                String questionSha=questionInDBVo.getSha();
                QuestionInCacheVo questionInCacheVo=
                        new QuestionInCacheVo(questionDetail,questionSha);
                questionCache.put(questionId,questionInCacheVo);
                return questionInCacheVo;
            }finally {
                ////无论正常还是异常，均要将生成题目的任务从缓存中移除
               processingQuestionCache.remove(questionId);
            }
        }
    }
}
