package cn.example.ch5.bitwise;

/**
 * ProjectName: jvm
 * packageName: cn.example.ch5.bitwise
 * ClassName: Permission
 *
 * @author: 李朋飞
 * @time: 2022/1/3 15:48
 *
 * 位运算运用：权限控制
 **/
public class Permission {
    //有符号左移<<(若正数,高位补0,负数,高位补1) 相当于乘2
    // 无符号右移>>>(不论正负,高位均补0)
    private static final int ALLOW_SELECT=1<<0;
    private static final int ALLOW_INSERT=1<<1;
    private static final int ALLOW_UPDATE=1<<2;
    private static final int ALLOW_DELETE=1<<3;

    private int flag;//当前权限状态
    public void setPermission(int permission){flag=permission;}
    //增加一项或者多项权限
    public void addPermission(int permission){flag=flag|permission;}

    //删除权限，
    public void disablePermission(int permission){flag=flag&~permission;}

    //是否拥有某些权限
    public boolean isAllow(int permission){return (flag&permission)==permission;}

    //是否不拥有某些权限
    public boolean isNowAllow(int permission){return (flag&permission)==0;}

    public static void main(String[] args) {
        int flag=15;
        Permission permission=new Permission();
        permission.setPermission(flag);
        permission.disablePermission(ALLOW_DELETE|ALLOW_INSERT);
        System.out.println("ALLOW__SELECT:"+permission.isAllow(ALLOW_SELECT));
        System.out.println("ALLOW__INSERT:"+permission.isAllow(ALLOW_INSERT));
        System.out.println("ALLOW__UPDATE:"+permission.isAllow(ALLOW_UPDATE));
        System.out.println("ALLOW__DELETE:"+permission.isAllow(ALLOW_DELETE));

    }
}
