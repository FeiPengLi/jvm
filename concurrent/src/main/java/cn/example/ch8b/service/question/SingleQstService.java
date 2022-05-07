package cn.example.ch8b.service.question;

import cn.example.ch8b.assist.SL_QuestionBank;

/**
 * ProjectName: jvm
 * packageName: cn.example.ch8b.service.question
 * ClassName: SingleQstService
 *
 * @author: 李朋飞
 * @time: 2022/1/22 下午 02:19
 * <p>
 * 模拟解析题目文本，下载图片等操作，返回解析后的文本
 **/
public class SingleQstService {

    public static String makeQuestion(Integer questionId) {
        return QstService.makeQuestion(questionId,
                SL_QuestionBank.getQuestion(questionId).getDetail());

    }
}
