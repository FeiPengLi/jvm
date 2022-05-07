package cn.example.ch7.tranfer;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * ProjectName: jvm
 * packageName: cn.example.ch7.tranfer
 * ClassName: UserAccount
 *
 * @author: 李朋飞
 * @time: 2022/1/16 上午 11:
 *
 * 用户账户的实体类
 **/
public class UserAccount {

    private final String name;

    private int money;

    private final Lock lock=new ReentrantLock();

    public Lock getLock() {
        return lock;
    }

    public UserAccount(String name, int money) {
        this.name = name;
        this.money = money;
    }

    public String getName() {
        return name;
    }

    public int getMoney() {
        return money;
    }

    @Override
    public String toString() {
        return "UserAccount{" +
                "name='" + name + '\'' +
                ", money=" + money +
                '}';
    }
    public void addMoney(int amount){money=money+amount;}
    public void flyMoney(int amount){money=money-amount;}
}
