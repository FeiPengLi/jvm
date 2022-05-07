package cn.example.ch8b.vo;

/**
 * ProjectName: jvm
 * packageName: cn.example.ch8b.vo
 * ClassName: QuestionInCacheVo
 *
 * @author: 李朋飞
 * @time: 2022/1/22 上午 09:42
 *
 * 题目保存在缓存中的实体
 **/
public class QuestionInCacheVo {

    private final String questionDetail;
    private final String questionSha;

    public QuestionInCacheVo(String questionDetail, String questionSha) {
        this.questionDetail = questionDetail;
        this.questionSha = questionSha;
    }

    public String getQuestionDetail() {
        return questionDetail;
    }

    public String getQuestionSha() {
        return questionSha;
    }
}
