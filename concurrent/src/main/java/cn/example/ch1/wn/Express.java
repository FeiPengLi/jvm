package cn.example.ch1.wn;

/**
 * ProjectName: jvm
 * packageName: cn.example.ch1.wn
 * ClassName: Express
 *
 * @author: 李朋飞
 * @time: 2021/12/12 18:05
 * 快递实体类,面试题等待通知模式
 **/
public class Express {

    public final static String CITY="Shanghai";
    private int km;//里程
    private String site;//当前到达地点

    public Express(int km, String site) {
        this.km = km;
        this.site = site;
    }

    public Express() {
    }
    //公里数变化，通知其他在wait的并需要处理公里数的线程进行业务处理
    public synchronized void changeKm(){
        this.km=101;
        notifyAll();
    }
    //地点变化，通知其他在wait并需要处理地点的线程进行业务处理
    public synchronized void changeSite(){
        this.site="Beijing";
        notifyAll();
    }

    //线程等待公里的变化
    public synchronized void waitKm(){
        while(km<100){
            try {
                wait();
                System.out.println("Check Km Thread["+Thread.currentThread().getName()+"]is be notified...");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("the Km is "+this.km+",I will change db,and doSomething...");
    }

    public synchronized void waitSite(){
        while(this.site.equals(CITY))   {
            try {
                wait();
                System.out.println("Check Site Thread{"+Thread.currentThread().getName()+"} is be notified...");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("the Site is "+this.site+",I will call user,and doSomething...");
    }
}
