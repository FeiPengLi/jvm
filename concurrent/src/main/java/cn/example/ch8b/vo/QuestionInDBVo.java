package cn.example.ch8b.vo;

/**
 * ProjectName: jvm
 * packageName: cn.example.ch8b.vo
 * ClassName: QuestionInDBVo
 *
 * @author: 李朋飞
 * @time: 2022/1/22 上午 09:44
 * .
 * 题目实体类
 **/
public class QuestionInDBVo {

    public  final int id;

    public  final String detail;

    private final String sha;

    public QuestionInDBVo(int id, String detail, String sha) {
        this.id = id;
        this.detail = detail;
        this.sha = sha;
    }

    public int getId() {
        return id;
    }

    public String getDetail() {
        return detail;
    }

    public String getSha() {
        return sha;
    }
}
