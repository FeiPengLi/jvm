package cn.example.ch5.bq;

import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * ProjectName: jvm
 * packageName: cn.example.ch5.bq
 * ClassName: ItemVo
 *
 * @author: 李朋飞
 * @time: 2022/1/3 16:18
 * 存放队列的元素
 **/
public class ItemVo<T> implements Delayed {
    private long activeTime;//到期时间，传入的数值代表过期的时长，单位毫秒

    private T data;//业务数据，泛型

    public ItemVo(long activeTime, T data) {
        this.activeTime = activeTime*1000+System.currentTimeMillis();
        this.data = data;
    }

    public long getActiveTime() {
        return activeTime;
    }

    public T getData() {
        return data;
    }

    //这个方法返回到激活日期的剩余时间，时间单位由单位参数指定
    @Override
    public long getDelay(TimeUnit unit) {
        long d=unit.convert(this.activeTime-System.currentTimeMillis(),unit);
        return d;
    }

    @Override
    public int compareTo(Delayed o) {
        long d=(getDelay(TimeUnit.MILLISECONDS)-o.getDelay(TimeUnit.MILLISECONDS));
        if (d==0)
            return 0;
        else
            if (d<0)
                return -1;
            else
                return 1;
    }
}
