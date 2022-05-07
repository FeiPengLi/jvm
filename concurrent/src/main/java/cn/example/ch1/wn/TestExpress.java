package cn.example.ch1.wn;

import java.util.zip.Checksum;

/**
 * ProjectName: jvm
 * packageName: cn.example.ch1.wn
 * ClassName: TestExpress
 *
 * @author: 李朋飞
 * @time: 2021/12/12 18:21
 **/
public class TestExpress {
    private static Express express=new Express(0,Express.CITY);

    private static class CheckKm extends Thread{
        @Override
        public void run() {
            express.waitKm();
        }
    }

    private static class CheckSite extends Thread{
        @Override
        public void run() {
            express.waitSite();
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i < 3; i++) {
            new CheckKm().start();
        }
        for (int i = 0; i < 3; i++) {
            new CheckSite().start();
        }

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        express.changeKm();
    }
}
