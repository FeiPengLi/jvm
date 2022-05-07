package cn.example.ch7.safeclass.safepublish;

import cn.example.ch7.safeclass.UserVo;

/**
 * ProjectName: jvm
 * packageName: cn.example.ch7.safeclass.safepublish
 * ClassName: SofePublicUser
 *
 * @author: 李朋飞
 * @time: 2022/1/15 下午 08:40
 *
 * 可以extends类再在方法加锁实现安全发布类
 **/
public class SofePublicUser {
    private final UserVo user;

    public SofePublicUser(UserVo user) {
        this.user = new SynUser(user);
    }

    public UserVo getUser() {
        return user;
    }

    private static class SynUser extends UserVo{
        private final UserVo userVo;
        private final Object lock =new Object();

        public SynUser(UserVo userVo) {
            this.userVo = userVo;
        }

        @Override
        public int getAge() {
            synchronized(lock){
                return userVo.getAge();
            }
        }

        @Override
        public void setAge(int age) {
            synchronized (lock){
                userVo.setAge(age);
            }
        }
    }
}
