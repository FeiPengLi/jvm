package cn.example.tools;

/**
 * ProjectName: jvm
 * packageName: cn.example.tools
 * ClassName: ErrorCode
 *
 * @author: 李朋飞
 * @time: 2021/12/10 22:39
 **/
public enum  ErrorCode {
    MAN{
        public void username(){
            System.out.println("男人");
        }
        public void username2(){
            System.out.println("男孩");
        }
    },
    WOMAN{

    };
    public void username(){
        System.out.println("老头");
    }
}
