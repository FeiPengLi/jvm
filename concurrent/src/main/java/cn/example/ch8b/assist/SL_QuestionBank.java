package cn.example.ch8b.assist;

import cn.example.ch8b.vo.QuestionInCacheVo;
import cn.example.ch8b.vo.QuestionInDBVo;

import java.security.SecureRandom;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * ProjectName: jvm
 * packageName: cn.example.ch8b.assist
 * ClassName: SL_QuestionBand
 *
 * @author: 李朋飞
 * @time: 2022/1/22 上午 10:39
 *
 * 模拟存储数据库中的题库数据
 **/
public class SL_QuestionBank {
    //题库数据存储
    private static ConcurrentHashMap<Integer, QuestionInDBVo>questionBankMap
    =new ConcurrentHashMap<>();

    //定时任务池，负责定时更新题库数据
    private static ScheduledExecutorService updateQuestionBank
            =new ScheduledThreadPoolExecutor(1);
    //初始化题库
    public static void initBank(){
        for (int i = 0; i < Consts.SIZE_OF_QUESTION_BANK; i++) {
            String questionContent=makeQuestionDetail(800);
            questionBankMap.put(i,new QuestionInDBVo(i,
                    questionContent,EncryptUtils.EncryptBySHA1(questionContent)));
        }
    }

    private static String makeQuestionDetail(int length) {
        String base="abcdefghijklmnopqrstuvwxyz0123456789";
        SecureRandom random=new SecureRandom();
        StringBuffer sb=new StringBuffer();
        for (int i = 0; i < length; i++) {
            int number=random.nextInt(base.length());
            sb.append(base.charAt(number));
        }
        return sb.toString();
    }
    //获得题目，假设一次数据库读取耗时在20ms，所以休眠20ms
    public static QuestionInDBVo getQuestion(int i){
        SL_Busi.buisness(20);
        return questionBankMap.get(i);//模拟数据库查询获取数据
    }
    //定期更新题库数据
    private static void UpdateQuestionTimer(){
        System.out.println("开始定时更新题库");
        updateQuestionBank.scheduleAtFixedRate(new UpdateBank(),
                15,5, TimeUnit.SECONDS);
    }
    //获取题目sha值，假设从数据库获取需要10ms，休眠10ms
    public static String getQuestionSha(int i){
        SL_Busi.buisness(10);
        return questionBankMap.get(i).getSha();
    }

    private static class UpdateBank implements Runnable{
        @Override
        public void run() {
            SecureRandom random=new SecureRandom();
            int questionId=random.nextInt(Consts.SIZE_OF_QUESTION_BANK  );
            String questionContent=makeQuestionDetail(700);
            questionBankMap.put(questionId,new QuestionInDBVo(questionId,
                    questionContent,EncryptUtils.EncryptBySHA1(questionContent)));
        }
    }
}
