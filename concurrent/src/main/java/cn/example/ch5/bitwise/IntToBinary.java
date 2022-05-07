package cn.example.ch5.bitwise;

/**
 * ProjectName: jvm
 * packageName: cn.example.ch5.bitwise
 * ClassName: IntToBinary
 *
 * @author: 李朋飞
 * @time: 2022/1/3 15:23
 *
 * 位运算
 **/
public class IntToBinary {

    public static void main(String[] args) {
        System.out.println("the -4 is "+Integer.toBinaryString(-4));
        System.out.println("the 4 is "+Integer.toBinaryString(4));
        System.out.println("the 5 is "+Integer.toBinaryString(5));
        System.out.println("the 6 is "+Integer.toBinaryString(6));
        System.out.println("the 4&6 is "+Integer.toBinaryString(4&6));
        System.out.println("the 4|6 is "+Integer.toBinaryString(4|6));
        System.out.println("the ~4 is "+Integer.toBinaryString(~4));
        System.out.println("the 4^6 is "+Integer.toBinaryString(4^6));
        System.out.println("the 4>>1 is "+Integer.toBinaryString(4>>1));
        System.out.println("the 4<<1 is "+Integer.toBinaryString(4<<1));
        System.out.println("the -4>>>4 is "+Integer.toBinaryString(-4>>>4));
        //取模a%(2^n)等价于a&(2^n-1)
        System.out.println("the 345 %16 is "+345%16+" or "+(345&(16-1)));
        System.out.println("Make hashCode is : "+"Make".hashCode()+"="+Integer.toBinaryString("Make".hashCode()));
        System.out.println("Bill hashCode is : "+"Bill".hashCode()+"="+Integer.toBinaryString("Bill".hashCode()));
    }
}
