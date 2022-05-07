package cn.example.ch4.rw;

import cn.example.tools.SleepTools;

/**
 * ProjectName: jvm
 * packageName: cn.example.ch4.rw
 * ClassName: UseSync
 *
 * @author: 李朋飞
 * @time: 2022/1/3 14:49
 * 类说明：用内置锁来实现商品服务接口
 **/
public class UseSync implements GoodService{

    private GoodsInfo goodsInfo;

    public UseSync(GoodsInfo goodsInfo) {
        this.goodsInfo = goodsInfo;
    }

    @Override
    public synchronized GoodsInfo getNum() {
        try {
            SleepTools.ms(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return this.goodsInfo;
    }

    @Override
    public synchronized void setNum(int nubmer) {
        try {
            SleepTools.ms(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        goodsInfo.changeNumber(nubmer);
    }
}
