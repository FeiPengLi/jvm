package cn.example.ch4.rw;

/**
 * ProjectName: jvm
 * packageName: cn.example.ch4.rw
 * ClassName: GoodsInfo
 *
 * @author: 李朋飞
 * @time: 2022/1/3 12:50
 **/
public class GoodsInfo {
    private final String name;
    private double  totalmoney;
    private int storeNumber;//库存

    public GoodsInfo(String name, double totalmoney, int storeNumber) {
        this.name = name;
        this.totalmoney = totalmoney;
        this.storeNumber = storeNumber;
    }

    public double getTotalmoney() {
        return totalmoney;
    }

    public void setTotalmoney(double totalmoney) {
        this.totalmoney = totalmoney;
    }

    public int getStoreNumber() {
        return storeNumber;
    }

    public void setStoreNumber(int storeNumber) {
        this.storeNumber = storeNumber;
    }

    public void changeNumber(int sellNumber){
        this.totalmoney+=sellNumber*25;
        this.storeNumber-=sellNumber;
    }
}
