package cn.example.ch8b;

import cn.example.ch8b.assist.CreatePendingDocs;
import cn.example.ch8b.assist.SL_QuestionBank;
import cn.example.ch8b.service.ProduceDocService;
import cn.example.ch8b.vo.SrcDocVo;

import java.util.List;

/**
 * ProjectName: jvm
 * packageName: cn.example.ch8b
 * ClassName: SingleWeb
 *
 * @author: 李朋飞
 * @time: 2022/1/22 下午 03:06
 **/
public class SingleWeb {
    public static void main(String[] args) {
        System.out.println("题库初始化。。。。。。");
        SL_QuestionBank.initBank();
        System.out.println("题库初始化完成。");
        List<SrcDocVo> docVoList= CreatePendingDocs.makePendingDoc(3);
        long startTotal=System.currentTimeMillis();
        for (SrcDocVo doc:docVoList){
            System.out.println("["+doc.getDocName()+"]共有题目："+doc.getQuestionList().size()+"个");
            System.out.println("开始处理文档："+doc.getDocName()+".......");
            long start=System.currentTimeMillis();
            String localName= ProduceDocService.makeDoc(doc);
            System.out.println("文档"+localName+"生成耗时："+(System.currentTimeMillis()-start)+"ms");
            start=System.currentTimeMillis();
            String remoteUrl=ProduceDocService.uploadDoc(localName);
            System.out.println("已上传至[ "+remoteUrl+"] 耗时："+(System.currentTimeMillis()-start)+"ms");
        }
        System.out.println("共耗时："+(System.currentTimeMillis()-startTotal)+"ms");
    }
}
