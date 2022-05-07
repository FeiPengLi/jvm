package cn.example.ch1.pool;

import java.sql.Connection;
import java.util.LinkedList;

/**
 * ProjectName: jvm
 * packageName: cn.example.ch1.pool
 * ClassName: DBPool
 *
 * @author: 李朋飞
 * @time: 2021/12/11 12:28
 **/
public class DBPool {
    //存放连接
    private static LinkedList<Connection> pool=new LinkedList<>();

    public DBPool(int initsize) {
        if (initsize>0){
            for (int i=0;i<initsize;i++){
                pool.addLast(SqlConnectImpl.fetchConnection());
            }
        }
    }

    //释放连接通知其他等待的线程
    public void releaseConnection(Connection connection){
        if (connection!=null){
            synchronized (pool){
                pool.addLast(connection);
                pool.notify();
            }
        }
    }

    public Connection fetchConnection(long mills) throws InterruptedException {
        synchronized (pool){
            //永不超时
            if (mills<=0){
                while (pool.isEmpty()){
                    pool.wait();

                }
                return pool.removeFirst();
            }else{
                //超时时刻
                long future=System.currentTimeMillis()+mills;
                //等待时常
                long remaining =mills;
                while (pool.isEmpty()&&remaining>0){
                    pool.wait();
                    //唤醒一次，重新计算等待时长
                    remaining=future-System.currentTimeMillis();

                }
                Connection connection=null;
                if (!pool.isEmpty()) {
                    connection=pool.removeFirst();
                }
                return connection;
            }
        }
    }
}
