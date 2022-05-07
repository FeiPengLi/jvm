package cn.example.ch7.tranfer.service;

import cn.example.ch7.tranfer.UserAccount;

/**
 * ProjectName: jvm
 * packageName: cn.example.ch7.tranfer.service
 * ClassName: ITransfer
 *
 * @author: 李朋飞
 * @time: 2022/1/16 上午 11:25
 *
 * 银行转账动作接口
 **/
public interface ITransfer {
    void transfer(UserAccount from,UserAccount to,int amount)throws InterruptedException;
}
