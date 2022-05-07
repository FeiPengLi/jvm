package org.example.ex6;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

/**
 * packageName:org.example.ex6
 * author:李朋飞
 * time:2021/12/4 11:39
 * ProjectName:jvm
 * ClassName: StreamDemo
 * finally字节码处理
 */
public class StreamDemo {
    public void read() {
        InputStream inputStream = null;
        try {
            inputStream=new FileInputStream("A.java");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }finally {
            if (null!=inputStream) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
