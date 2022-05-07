package cn.example.ch7.tranfer.service;

import cn.example.ch7.tranfer.UserAccount;

import java.util.Random;

/**
 * ProjectName: jvm
 * packageName: cn.example.ch7.tranfer.service
 * ClassName: SafeOperateTwo
 *
 * @author: 李朋飞
 * @time: 2022/1/16 上午 11:49
 *
 * 不会产生死锁的第二种方法。
 **/
public class SafeOperateTwo implements ITransfer{
    @Override
    public void transfer(UserAccount from, UserAccount to, int amount) throws InterruptedException {
        Random r=new Random();
        while(true){
            if (from.getLock().tryLock()){
                System.out.println(Thread.currentThread().getName()+" get "+ from.getName());
                try {
                    if (to.getLock().tryLock()){
                       try {
                           System.out.println(Thread.currentThread().getName()+" get "+to.getName());
                           from.flyMoney(amount);
                           to.addMoney(amount);
                           System.out.println(from);
                           System.out.println(to);
                           break;
                       }finally {
                           to.getLock().unlock();
                       }
                    }
                }finally {
                    from.getLock().unlock();
                }
            }
            Thread.sleep(r.nextInt(2));
        }
    }
}
