package cn.example.ch8b.service.question;

import cn.example.ch8b.assist.SL_Busi;

import java.security.SecureRandom;

/**
 * ProjectName: jvm
 * packageName: cn.example.ch8b.service.question
 * ClassName: QstService
 *
 * @author: 李朋飞
 * @time: 2022/1/22 下午 02:12
 *
 * 单个题目处理的服务类
 **/
public class QstService {

    public static String makeQuestion(Integer questionId,String questionSrc){
        SecureRandom random=new SecureRandom();
        SL_Busi.buisness(450+random.nextInt(100));
        return "CompleteQuestion[id="+questionId+
                " content="+questionSrc+"]";
    }
}
