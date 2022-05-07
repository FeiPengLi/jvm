package org.example.ex2;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * packageName:org.example.ex2
 * author:李朋飞
 * time:2021/11/30 21:35
 * ProjectName:jvm
 * ClassName: MethodAreaOutOfMemory
 * vm args: -XX:MetaspaceSize=10M -XX:MaxMetaspaceSize=10M
 * 方法区导致的内存溢出：方法区10M大小
 */
public class MethodAreaOutOfMemory {
    public static void main(String[] args) {
        while (true) {
            Enhancer enhancer = new Enhancer();
            enhancer.setSuperclass(TestObject.class);
            enhancer.setUseCache(false);
            enhancer.setCallback(new MethodInterceptor() {
                @Override
                public Object intercept(Object o, Method arg1, Object[] Object, MethodProxy methodProxy) throws Throwable {
                    return methodProxy.invokeSuper(o, Object);
                }
            });
            enhancer.create();
        }
    }

    public static class TestObject {
        private double a = 20.102;
        private Integer b = 999999;
    }
}
