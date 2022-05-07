package cn.example.ch2.tools.semaphore;

import cn.example.ch1.pool.SqlConnectImpl;

import java.sql.Connection;
import java.util.LinkedList;
import java.util.concurrent.Semaphore;

/**
 * ProjectName: jvm
 * packageName: cn.example.ch2.tools.semaphore
 * ClassName: DBPoolSemaphore
 *
 * @author: 李朋飞
 * @time: 2021/12/25 16:34
 * Semaphore用法，一个数据库连接示例
 **/
public class DBPoolSemaphore {

    private  static final int POOL_SIZE=10;

    private final Semaphore useful,useless;
    private static LinkedList<Connection>pool=new LinkedList<>();
    static{
        for (int i = 0; i <POOL_SIZE; i++) {
            pool.addLast(SqlConnectImpl.fetchConnection());

        }
    }
    public DBPoolSemaphore() {
        this.useful = new Semaphore(10);
        this.useless = new Semaphore(0);
    }
    //归还连接  、
    public void returnConnect(Connection connection) throws InterruptedException {
        if (connection!=null){
            System.out.println("当前有："+useful.getQueueLength()+"个线程等待数据库连接！可用连接数："+useful.availablePermits());
            useless.acquire();
            synchronized (pool){
                pool.addLast(connection);
            }
            useful.release();
        }
    }

    public  Connection takeConnect() throws InterruptedException {
        useful.acquire();
        Connection connection;
        synchronized (pool){
            connection= pool.removeFirst();

        }
        useless.release();
        return connection;
    }
}
