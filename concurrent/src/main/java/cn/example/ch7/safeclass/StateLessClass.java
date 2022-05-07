package cn.example.ch7.safeclass;

/**
 * ProjectName: jvm
 * packageName: cn.example.ch7.safeclass
 * ClassName: StateLessClass
 *
 * @author: 李朋飞
 * @time: 2022/1/15 下午 08:12
 *
 * 无状态的类；即类中无成员变量只有方法
 **/
public class StateLessClass {

    public int service (int a,int b){
        return a+b;
    }
    public void serviceUser(UserVo userVo){

    }
}
