package cn.example.ch8b;

import cn.example.ch8b.assist.Consts;
import cn.example.ch8b.assist.CreatePendingDocs;
import cn.example.ch8b.assist.SL_QuestionBank;
import cn.example.ch8b.service.ProduceDocService;
import cn.example.ch8b.vo.SrcDocVo;

import java.util.List;
import java.util.concurrent.*;

/**
 * ProjectName: jvm
 * packageName: cn.example.ch8b
 * ClassName: RpcServiceWebV2
 *
 * @author: 李朋飞
 * @time: 2022/1/22 下午 07:56
 *
 * 题目的并行化，引入缓存
 **/
public class RpcServiceWebV2 {
    //处理文档生成的线程池
    private static ExecutorService docMakeService
            = Executors.newFixedThreadPool(Consts.THREAD_COUNT*2);

    //处理文档上传的线程池
    private static ExecutorService docUploadService
            =Executors.newFixedThreadPool(Consts.THREAD_COUNT*2);

    private static CompletionService<String>docMakeCompletingService
            =new ExecutorCompletionService<>(docMakeService);
    private static CompletionService<String>docUploadCompletionService
            =new ExecutorCompletionService<>(docUploadService);

    public static void main(String[] args) throws InterruptedException, ExecutionException {
        int docCount=60;
        System.out.println("题库开始初始化。。。。。。");
        SL_QuestionBank.initBank();
        System.out.println("题库初始化完成。。。。。。");
        List<SrcDocVo> docVoList= CreatePendingDocs.makePendingDoc(docCount);
        long startTotal=System.currentTimeMillis();
        for (SrcDocVo doc:docVoList){
            docMakeCompletingService.submit(new MakeDocTask(doc));
        }
        for (int i=0;i<docCount;i++){
            Future<String>future=docMakeCompletingService.take();
            docUploadCompletionService.submit(new UploadTask(future.get()));
        }
        //展示时间
        for (int i = 0; i <docCount; i++) {
            docUploadCompletionService.take().get();
        }
        System.out.println("共耗时："+(System.currentTimeMillis()-startTotal)+"ms");
        docMakeService.shutdown();
        docUploadService.shutdown();
    }
    //生成文档的工作任务
    private static class MakeDocTask implements Callable<String> {
        private SrcDocVo pendingDocVo;

        public MakeDocTask(SrcDocVo doc) {
            this.pendingDocVo=doc;
        }

        @Override
        public String call() throws Exception {
            long start=System.currentTimeMillis();
            String result= ProduceDocService.makeDocAsync(pendingDocVo);
            System.out.println("文档【"+result+"】生成耗时： "+(System.currentTimeMillis()-start)+"ms");
            return result;
        }
    }

    //处理文档上传的工作任务
    private static class UploadTask implements Callable<String>{
        private String fileName;

        public UploadTask(String fileName) {
            this.fileName = fileName;
        }

        @Override
        public String call() throws Exception {
            long start=System.currentTimeMillis();
            String result=ProduceDocService.uploadDoc(fileName);
            System.out.println("已上传至【"+result+"】生成耗时：  "+
                    (System.currentTimeMillis()-start)+"ms");
            return result;
        }
    }
}
