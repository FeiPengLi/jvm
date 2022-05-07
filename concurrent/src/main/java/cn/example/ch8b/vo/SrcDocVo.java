package cn.example.ch8b.vo;

import java.util.List;

/**
 * ProjectName: jvm
 * packageName: cn.example.ch8b.vo
 * ClassName: SrcDocVo
 *
 * @author: 李朋飞
 * @time: 2022/1/22 上午 09:46
 *
 * 待处理文档实体类
 **/
public class SrcDocVo {

    private final String docName;

    private final List<Integer> questionList;

    public SrcDocVo(String docName, List<Integer> questionList) {
        this.docName = docName;
        this.questionList = questionList;
    }

    public String getDocName() {
        return docName;
    }

    public List<Integer> getQuestionList() {
        return questionList;
    }
}
