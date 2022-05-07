package cn.example.ch7.safeclass;

/**
 * ProjectName: jvm
 * packageName: cn.example.ch7.safeclass
 * ClassName: ImmutaleClass
 *
 * @author: 李朋飞
 * @time: 2022/1/15 下午 08:06
 *
 * 类不可变
 **/
public class ImmutaleClass {
    private final int a;
    private final UserVo user = new UserVo();//不安全
    //只能get或使用构造初始化a，称为不可变的类
    public int getA() {
        return a;
    }

    public ImmutaleClass(int a) {
        this.a = a;
    }
    public static class User{
        private int age;

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }
    }
}
