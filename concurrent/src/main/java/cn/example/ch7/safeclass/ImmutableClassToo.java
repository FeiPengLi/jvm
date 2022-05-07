package cn.example.ch7.safeclass;

import java.util.ArrayList;
import java.util.List;

/**
 * ProjectName: jvm
 * packageName: cn.example.ch7.safeclass
 * ClassName: ImmutableClassToo
 *
 * @author: 李朋飞
 * @time: 2022/1/15 下午 08:09
 **/
public class ImmutableClassToo {
    private final List<Integer> list=new ArrayList<>();
    public ImmutableClassToo() {
        list.add(1);
        list.add(2);
        list.add(3);
    }
    public boolean isContain(int i){
        return list.contains(i);
    }
}
