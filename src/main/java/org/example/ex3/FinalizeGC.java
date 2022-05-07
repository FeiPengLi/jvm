package org.example.ex3;

/**
 * packageName:org.example.ex3
 * author:李朋飞
 * time:2021/12/4 10:08
 * ProjectName:jvm
 * ClassName: FinalizeGC
 * 对象的自我拯救
 */
public class FinalizeGC {
    public static FinalizeGC instance=null;
    public void isAlive() throws Throwable {
        System.out.println(" i am still alive");
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
        System.out.println("Finalize method executed");
        FinalizeGC.instance=this;
    }

    public static void main(String[] args) throws Throwable {
        instance=new FinalizeGC();
        instance=null;
        System.gc();//触发gc
        Thread.sleep(1000);
        if (instance!=null)
            instance.isAlive();
        else System.out.println(" i am dead ");
        instance=null;
        System.gc();
        Thread.sleep(1000);
        if (instance!=null)
            System.out.println(" no dead!!");
        else System.out.println(" i am deaded!!!!");
    }
}
