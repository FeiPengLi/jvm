package org.example.ex2;

/**
 * packageName:org.example.ex2
 * author:李朋飞
 * time:2021/11/30 22:16
 * ProjectName:jvm
 * ClassName: JVMStack
 */
public class JVMStack {
//    jvm对栈帧的空间优化
    public int work(int x) throws InterruptedException {
        int z=(x+5) *10;
        Thread.sleep(Integer.MAX_VALUE);
        return z;
    }

    public static void main(String[] args) throws InterruptedException {
        JVMStack jvmStack=new JVMStack();
        jvmStack.work(10);//放入main栈帧 10：操作数栈
    }
}
