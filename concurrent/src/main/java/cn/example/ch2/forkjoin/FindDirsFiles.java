package cn.example.ch2.forkjoin;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveAction;

/**
 * ProjectName: jvm
 * packageName: cn.example.ch2.forkjoin
 * ClassName: FindDirsFiles
 *
 * @author: 李朋飞
 * @time: 2021/12/25 10:04
 * 遍历指定目录，找寻指定类型文件
 **/
public class FindDirsFiles  extends RecursiveAction {
    private File path;

    public FindDirsFiles(File path) {
        this.path = path;
    }

    @Override
    protected void compute() {
        List<FindDirsFiles> subTasks=new ArrayList<>();
        File[]files=path.listFiles();
        if (files!=null){
            for (File file:files) {
                if (file.isDirectory()){
                    subTasks.add(new FindDirsFiles(file));
                }else{
                    if (file.getAbsolutePath().endsWith("txt")){
                        System.out.println("文件："+file.getAbsolutePath());
                    }
                }

            }
            if (!subTasks.isEmpty()){
                //在当前的forkjoinpool 上调度所有的子任务
                for (FindDirsFiles findDirsFiles: invokeAll(subTasks)){
                    findDirsFiles.join();
                }
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        ForkJoinPool pool=new ForkJoinPool();
        FindDirsFiles task=new FindDirsFiles(new File("D:/"));
        pool.execute(task);
        System.out.println("task is running......");
        Thread.sleep(1);
        int otherWork=0;
        for (int i=0;i<100;i++){

            otherWork=otherWork+i;
        }
        System.out.println(" main thread done sth.....,otherWork="+otherWork);
        task.join();//阻塞方法，不然不会执行查找文件操作
        System.out.println(" task end");
    }
}
