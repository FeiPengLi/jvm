package cn.example.ch9;

/**
 * ProjectName: jvm
 * packageName: cn.example.ch9
 * ClassName: ControDep
 *
 * @author: 李朋飞
 * @time: 2022/1/22 下午 09:03
 *
 * 控制依赖
 **/
public class ControDep {
    int a=0;
    volatile boolean flag=true;

   public void  init() {
        a=1;       //1
        flag=true; //2
    }
    public synchronized void use(){
       if (flag){      //3
           int i=a*a; //4
       }
    }
}
