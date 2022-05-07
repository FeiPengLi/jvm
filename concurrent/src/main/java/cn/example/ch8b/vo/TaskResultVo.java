package cn.example.ch8b.vo;

import java.util.concurrent.Future;

/**
 * ProjectName: jvm
 * packageName: cn.example.ch8b.vo
 * ClassName: TaskResultVo
 *
 * @author: 李朋飞
 * @time: 2022/1/22 上午 09:47
 *
 * 并发处理返回的题目结果实体类
 **/
public class TaskResultVo {

    private final String questionDetail;

    private final Future<QuestionInCacheVo>questionFuture;

    public TaskResultVo(String questionDetail, Future<QuestionInCacheVo> questionFuture) {
        this.questionDetail = questionDetail;
        this.questionFuture = questionFuture;
    }

    public TaskResultVo(String questionDetail) {
        this.questionDetail = questionDetail;
        this.questionFuture=null;
    }

    public TaskResultVo(Future<QuestionInCacheVo> questionFuture) {
        this.questionFuture = questionFuture;
        this.questionDetail=null;
    }

    public String getQuestionDetail() {
        return questionDetail;
    }

    public Future<QuestionInCacheVo> getQuestionFuture() {
        return questionFuture;
    }
}
