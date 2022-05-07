package cn.example.ch7.dcl;

/**
 * ProjectName: jvm
 * packageName: cn.example.ch7.dcl
 * ClassName: InstancelLazy
 *
 * @author: 李朋飞
 * @time: 2022/1/16 上午 10:24
 * 在域上运用延迟初始化占位类模式
 **/
public class InstancelLazy {
    private Integer value;

    private Integer heavy;//成员变量很耗费资源

    public InstancelLazy(Integer value) {
        super();
        this.value = value;
    }
    private static class InstanceHolder{
        public static Integer val=new Integer(100);
    }

    public Integer getValue() {
        return value;
    }

    public Integer getHeavy() {
        return heavy;
    }
}
