package org.example.ex9.gengric;

/**
 * ProjectName: jvm
 * packageName: org.example.ex9.gengric
 * ClassName: WhyNeedGeneric
 *
 * @author: 李朋飞
 * @time: 2021/12/4 18:17
 * 为什么需要泛型
 **/
public class WhyNeedGeneric {

    public int addInt(int x, int y){
        return x+y;
    }
    public double adddouble(double x,double y){
        return x+y;
    }

    public <T extends Number> double add(T x,T y){
        return x.doubleValue()+y.doubleValue();
    }
    public static void main(String[] args) {
        //不适用泛型
        WhyNeedGeneric needGeneric=new WhyNeedGeneric();
        System.out.println(needGeneric.addInt(1,2));
        System.out.println(needGeneric.adddouble(1.0f,1.3f));

        System.out.println("--------------------");
        System.out.println(needGeneric.add(2,3));
        System.out.println(needGeneric.add(1.2d,1.3d));
    }
}
