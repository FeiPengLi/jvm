package cn.example.ch8b.service;

import cn.example.ch8b.assist.SL_Busi;
import cn.example.ch8b.service.question.ParallelQstService;
import cn.example.ch8b.service.question.SingleQstService;
import cn.example.ch8b.vo.SrcDocVo;
import cn.example.ch8b.vo.TaskResultVo;

import java.security.SecureRandom;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;

/**
 * ProjectName: jvm
 * packageName: cn.example.ch8b.service
 * ClassName: ProduceDocService
 *
 * @author: 李朋飞
 * @time: 2022/1/22 下午 02:23
 *
 * 处理文档的任务
 **/
public class ProduceDocService {

    /**
     * 上传文档到网络
     * @param docFileName 实际文档在本地的存储位置
     * @return 上传后的网络存储地址
     */
    public static String uploadDoc(String docFileName){
        SecureRandom radom=new SecureRandom();
        SL_Busi.buisness(9000+radom.nextInt(400));
        return "http://www.xxxx.com/file/uploads/"+docFileName;
    }

    /**
     * 将待处理的文档处理为本地实际文档
     * @param pendingDocVo 待处理文档
     * @return 实际文档在本地的存储位置
     */
    public static String makeDoc(SrcDocVo pendingDocVo){
        System.out.println("开始处理文档："+pendingDocVo.getDocName());
        StringBuffer sb=new StringBuffer();
        for (Integer questionId: pendingDocVo.getQuestionList()){
            sb.append(SingleQstService.makeQuestion(questionId));
        }
        return "complete_"+System.currentTimeMillis()+"_"+
                pendingDocVo.getDocName()+".pdf";
    }

    public static String makeDocAsync(SrcDocVo pendingDocVo) throws ExecutionException, InterruptedException {
        System.out.println("开始处理文档："+pendingDocVo.getDocName());
        //每个题目的处理结果
        Map<Integer, TaskResultVo> qstResultMap=new HashMap<>();
        for (Integer questionId:pendingDocVo.getQuestionList()){
            qstResultMap.put(questionId, ParallelQstService.makeQuestion(questionId));


        }
        StringBuffer sb=new StringBuffer();
        for (Integer questionId:pendingDocVo.getQuestionList()){
            TaskResultVo taskResultVo=qstResultMap.get(questionId);
            sb.append(taskResultVo.getQuestionDetail()==null?taskResultVo.getQuestionFuture().get().getQuestionDetail():
                    taskResultVo.getQuestionDetail());
        }
        return "complete_"+System.currentTimeMillis()+"_"+
                pendingDocVo.getDocName()+".pdf";
    }
}
