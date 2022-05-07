package org.example.ex8;

/**
 * packageName:org.example.ex8
 * author:李朋飞
 * time:2021/12/4 16:23
 * ProjectName:jvm
 * ClassName: Invoke
 */
public class Invoke implements I{
    @Override
    public void inf() {

    }

    public static void main(String[] args) {
        Invoke invoke=new Invoke();
        invoke.inf();
    }
}
