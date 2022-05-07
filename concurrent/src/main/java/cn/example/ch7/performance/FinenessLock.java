package cn.example.ch7.performance;

import java.util.HashSet;
import java.util.Set;

/**
 * ProjectName: jvm
 * packageName: cn.example.ch7.performance
 * ClassName: FinenessLock
 *
 * @author: 李朋飞
 * @time: 2022/1/16 上午 10:53
 * <p>
 * 锁分离
 **/
public class FinenessLock {

    public final Set<String> users = new HashSet<>();
    public final Set<String> queries = new HashSet<>();

    public synchronized void add(String u) {
        users.add(u);
    }

    public synchronized void addQuery(String q) {
        boolean add = queries.add(q);

    }

    public synchronized void removeUser(String u) {
        users.remove(u);
    }
    public synchronized void remove(String q){
        queries.remove(q);
    }

    public void adduserDiv(String u){
        synchronized (users){
            users.add(u);
        }
    }

    public void addQueryDiv(String q){
        synchronized (queries){
            queries.add(q);
        }
    }

}
