package cn.example.ch4.rw;

import cn.example.tools.SleepTools;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * ProjectName: jvm
 * packageName: cn.example.ch4.rw
 * ClassName: UseRwLock
 *
 * @author: 李朋飞
 * @time: 2022/1/3 13:18
 **/

public class UseRwLock implements GoodService{

    private GoodsInfo goodsInfo;
    private final ReadWriteLock lock =new ReentrantReadWriteLock();
    private final Lock getLock=lock.readLock();//读锁
    private final Lock setLock=lock.writeLock();//写锁

    public UseRwLock(GoodsInfo goodsInfo) {
        this.goodsInfo = goodsInfo;
    }

    @Override
    public GoodsInfo getNum() {
        getLock.lock();
        try {
            try {
                SleepTools.ms(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return this.goodsInfo;
        }finally {
            getLock.unlock();
        }
    }

    @Override
    public void setNum(int nubmer) {
        setLock.lock();
        try {
            try {
                SleepTools.ms(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            goodsInfo.changeNumber(nubmer);
        }finally {
            setLock.unlock();
        }
    }
}
