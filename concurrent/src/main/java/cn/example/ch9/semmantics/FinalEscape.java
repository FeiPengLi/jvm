package cn.example.ch9.semmantics;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * ProjectName: jvm
 * packageName: cn.example.ch9.semmantics
 * ClassName: FinalEscape
 *
 * @author: 李朋飞
 * @time: 2022/1/22 下午 09:04
 *
 * 不能让final引用从构造方法中溢出
 **/
public class FinalEscape {
    final int i;
    static FinalEscape obj;

    public FinalEscape() {
        this.i = 10;//写final域
        obj=this;   //this引用溢出
    }
    public static void writer(){new FinalEscape();}
    public static int reader(){
        if (obj!=null){     //3
            int temp=obj.i; //4
           return temp;
//           return obj.i;
        }
        return -1;
    }

    public static void main(String[] args) {
        //System.out.println(reader());
String rex="((^((1[8-9]\\d{2})|([2-9]\\d{3}))([-\\/\\._])(10|12|0?[13578])([-\\/\\._])(3[01]|[12][0-9]|0?[1-9])$)|(^((1[8-9]\\d{2})|([2-9]\\d{3}))([-\\/\\._])(11|0?[469])([-\\/\\._])(30|[12][0-9]|0?[1-9])$)|(^((1[8-9]\\d{2})|([2-9]\\d{3}))([-\\/\\._])(0?2)([-\\/\\._])(2[0-8]|1[0-9]|0?[1-9])$)|(^([2468][048]00)([-\\/\\._])(0?2)([-\\/\\._])(29)$)|(^([3579][26]00)([-\\/\\._])(0?2)([-\\/\\._])(29)$)|(^([1][89][0][48])([-\\/\\._])(0?2)([-\\/\\._])(29)$)|(^([2-9][0-9][0][48])([-\\/\\._])(0?2)([-\\/\\._])(29)$)|(^([1][89][2468][048])([-\\/\\._])(0?2)([-\\/\\._])(29)$)|(^([2-9][0-9][2468][048])([-\\/\\._])(0?2)([-\\/\\._])(29)$)|(^([1][89][13579][26])([-\\/\\._])(0?2)([-\\/\\._])(29)$)|(^([2-9][0-9][13579][26])([-\\/\\._])(0?2)([-\\/\\._])(29)$))";
        Matcher m= Pattern.compile(rex).matcher("1850-02-12");
        if (m.matches()){
            System.out.println("true");
        }
        else
            System.out.println("false");
    }
}
