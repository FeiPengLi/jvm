package cn.example.ch7.safeclass.safepublish;

/**
 * ProjectName: jvm
 * packageName: cn.example.ch7.safeclass.safepublish
 * ClassName: SafePublicFinalUser
 *
 * @author: 李朋飞
 * @time: 2022/1/15 下午 08:46
 *
 * 类定义为final后委托给线程安全的类来做，或者可以extends类实现安全发布
 **/
public class SafePublicFinalUser {
    private final SyncFinalUser user;

    public SyncFinalUser getUser() {
        return user;
    }

    public SafePublicFinalUser(SyncFinalUser user) {
        this.user = user;
    }

    public static class SyncFinalUser{
        private final FinalUserVo userVo;
        private final Object lock =new Object();

        public SyncFinalUser(FinalUserVo userVo) {
            this.userVo = userVo;
        }
        public int getAge() {
            synchronized (lock){
                return userVo.getAge();
            }
        }

        public void setAge(int age) {
            synchronized (lock){
                userVo.setAge(age);
            }
        }
    }
}
