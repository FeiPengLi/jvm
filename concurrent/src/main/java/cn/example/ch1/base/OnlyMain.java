package cn.example.ch1.base;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;

/**
 * ProjectName: jvm
 * packageName: cn.example.ch1.base
 * ClassName: OnlyMain
 *
 * @author: 李朋飞
 * @time: 2021/12/11 10:14
 * 只有一个main方法的程序
 **/
public class OnlyMain {
    public static void main(String[] args) {
        //java 虚拟机线程系统的管理接口
        ThreadMXBean threadMXBean= ManagementFactory.getThreadMXBean();
        //不需要获取同步的monitor和synchronizer信息，仅仅获取线程和线程堆栈信息
        ThreadInfo[] threadInfos=threadMXBean.dumpAllThreads(false,false);
        // 遍历线程信息，仅打印线程ID和线程名称信息
        for (ThreadInfo threadInfo: threadInfos
             ) {
            System.out.println("["+threadInfo.getThreadId()+"]"+threadInfo.getThreadName());
        }
    }
}
