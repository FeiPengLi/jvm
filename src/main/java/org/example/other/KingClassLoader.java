package org.example.other;

import java.io.*;

/**
 * packageName:org.example.other
 * author:李朋飞
 * time:2021/12/4 12:10
 * ProjectName:jvm
 * ClassName: KingClassLoader
 */
public class KingClassLoader extends ClassLoader {
    private String path;
    private String classloaderName;

    public KingClassLoader(ClassLoader parent, String path, String classloaderName) {
        super(parent);
        this.path = path;
        this.classloaderName = classloaderName;
    }

    public KingClassLoader(String path, String classloaderName) {
        this.path = path;
        this.classloaderName = classloaderName;
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        byte[] b = loadClassData(name);
        return defineClass(name, b, 0, b.length);
    }

    private byte[] loadClassData(String name) {
        name = path + name + ".class";
        InputStream in = null;
        ByteArrayOutputStream out = null;
        try {
            in = new FileInputStream(new File(name));
            out = new ByteArrayOutputStream();
            int i = 0;
            while ((i = in.read()) != -1) {
                out.write(i);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                out.close();
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return out.toByteArray();
    }
}
