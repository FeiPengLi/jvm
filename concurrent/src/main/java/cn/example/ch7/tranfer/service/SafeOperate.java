package cn.example.ch7.tranfer.service;

import cn.example.ch7.tranfer.UserAccount;

/**
 * ProjectName: jvm
 * packageName: cn.example.ch7.tranfer.service
 * ClassName: SafeOperate
 *
 * @author: 李朋飞
 * @time: 2022/1/16 上午 11:33
 *
 * 不会产生死锁的安全转账
 **/
public class SafeOperate implements ITransfer{

    private static Object tieLock=new Object();//第三把锁
    @Override
    public void transfer(UserAccount from, UserAccount to, int amount) throws InterruptedException {
     int fromHash=System.identityHashCode(from);
     int toHash=System.identityHashCode(to);
     if (fromHash<toHash){
         synchronized (from){
             System.out.println(Thread.currentThread().getName()+" get "+from.getName());
             Thread.sleep(100);
             synchronized (to){
                 System.out.println(Thread.currentThread().getName()+" get "+to.getName());
                 from.flyMoney(amount);
                 to.addMoney(amount);
                 System.out.println(from);
                 System.out.println(to);
             }
         }
     }else if (toHash<fromHash){
         synchronized (to){
             System.out.println(Thread.currentThread().getName()+" get "+to.getName());
             Thread.sleep(100);
             synchronized (from){
                 System.out.println(Thread.currentThread().getName()+" get "+from.getName());
                 from.flyMoney(amount);
                 to.addMoney(amount);
                 System.out.println(from);
                 System.out.println(to);
             }
         }
     }else{
         synchronized (tieLock){
             synchronized (from){
                 synchronized (to){
                     from.flyMoney(amount);
                     to.addMoney(amount);
                 }
             }
         }
     }
    }
}
