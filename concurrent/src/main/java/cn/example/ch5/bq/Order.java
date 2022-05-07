package cn.example.ch5.bq;

/**
 * ProjectName: jvm
 * packageName: cn.example.ch5.bq
 * ClassName: Order
 *
 * @author: 李朋飞
 * @time: 2022/1/3 16:14
 *
 * 订单实体类
 **/
public class Order {
    private final String orderNo;//订单编号
    private final double orderMoney;//订单金额

    public Order(String orderNo, double orderMoney) {
        this.orderNo = orderNo;
        this.orderMoney = orderMoney;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public double getOrderMoney() {
        return orderMoney;
    }
}
