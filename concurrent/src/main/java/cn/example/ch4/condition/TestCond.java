package cn.example.ch4.condition;

/**
 * ProjectName: jvm
 * packageName: cn.example.ch4.condition
 * ClassName: TestCond
 *
 * @author: 李朋飞
 * @time: 2022/1/3 11:26
 **/
public class TestCond {
    private static ExpressCond express=new ExpressCond(0,ExpressCond.CITY);

    private static class CheckKm extends Thread{
        @Override
        public void run() {
            express.waitekm();
        }
    }
    private static class CheckSite extends Thread{
        @Override
        public void run() {
            express.waiteSite();
        }
    }

    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i < 3; i++) {
            new CheckKm().start();
        }
        for (int i = 0; i < 3; i++) {
            new CheckSite().start();
        }
        Thread.sleep(1000);
        express.changekm();
    }
}
