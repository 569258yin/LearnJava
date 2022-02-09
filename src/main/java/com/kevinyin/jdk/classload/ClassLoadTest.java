package com.kevinyin.jdk.classload;

import java.net.URL;

/**
 * @author yinhao
 * @Description TODO
 * @Date 2022/1/19 11:17
 */
public class ClassLoadTest {

    public static void main(String[] args) {
        //获取系统类加载器
        ClassLoader systemClassLoader = ClassLoader.getSystemClassLoader();
        //sun.misc.Launcher$AppClassLoader@135fbaa4
        System.out.println(systemClassLoader);

        //获取其上层：扩展类加载器
        ClassLoader extClassLoader = systemClassLoader.getParent();
        //sun.misc.Launcher$ExtClassLoader@2503dbd3
        System.out.println(extClassLoader);

        //再获取其上层：获取不到引导类加载器
        ClassLoader bootstrapClassLoader = extClassLoader.getParent();
        //null
        System.out.println(bootstrapClassLoader);

        //对于用户自定义类来说，默认使用系统类加载器进行加载，输出和systemClassLoader一样
        ClassLoader classLoader = ClassLoadTest.class.getClassLoader();
        //sun.misc.Launcher$AppClassLoader@135fbaa4
        System.out.println(classLoader);

        //String 类使用引导类加载器进行加载。Java的核心类库都使用引导类加载器进行加载，所以也获取不到
        ClassLoader classLoader1 = String.class.getClassLoader();
        //null
        System.out.println(classLoader1);

        //获取BootstrapClassLoader可以加载的api的路径
        URL[] urls = sun.misc.Launcher.getBootstrapClassPath().getURLs();
        for (URL url : urls) {
            System.out.println(url.toExternalForm());
        }
    }
}
