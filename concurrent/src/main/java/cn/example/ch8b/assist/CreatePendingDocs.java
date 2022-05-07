package cn.example.ch8b.assist;

import cn.example.ch8b.vo.SrcDocVo;

import java.security.SecureRandom;
import java.util.LinkedList;
import java.util.List;

/**
 * ProjectName: jvm
 * packageName: cn.example.ch8b.assist
 * ClassName: CreatePendingDocs
 *
 * @author: 李朋飞
 * @time: 2022/1/21 下午 09:29
 *
 * 生成待处理文档
 **/
public class CreatePendingDocs {

    public static List<SrcDocVo>makePendingDoc(int count){
        SecureRandom r=new SecureRandom();
        List<SrcDocVo>docList=new LinkedList<>();
        for (int i=0;i<count;i++){
            List<Integer>questionList=new LinkedList<>();
            int questNum=r.nextInt(60)+Consts.QUESTION_COUNT_IN_DOC;
            for (int j = 0; j < questNum; j++) {
                int questionId=r.nextInt(Consts.SIZE_OF_QUESTION_BANK);
                questionList.add(questionId);
            }
            SrcDocVo pendingDocVo=new SrcDocVo("pending_"+i,questionList);
            docList.add(pendingDocVo);
        }
        return docList;
    }
}
