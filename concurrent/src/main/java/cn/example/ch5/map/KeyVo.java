package cn.example.ch5.map;

import java.util.Objects;

/**
 * ProjectName: jvm
 * packageName: cn.example.ch5.map
 * ClassName: KeyVo
 *
 * @author: 李朋飞
 * @time: 2022/1/3 18:07
 **/
public class KeyVo {

    private final int id;
    private final String name;

    public KeyVo(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        KeyVo keyVo = (KeyVo) o;
        return id == keyVo.id &&
                Objects.equals(name, keyVo.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
