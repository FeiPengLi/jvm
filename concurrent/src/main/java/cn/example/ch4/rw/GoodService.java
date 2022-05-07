package cn.example.ch4.rw;

/**
 * ProjectName: jvm
 * packageName: cn.example.ch4.rw
 * ClassName: GoodService
 *
 * @author: 李朋飞
 * @time: 2022/1/3 12:49
 *
 * 商品的服务的接口
 **/
public interface GoodService {
    GoodsInfo getNum();//获得商品信息
    public void setNum(int nubmer);//设置商品数量
}
