package com.kevinyin.proxy.jdk;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class PeopleProxyHandler implements InvocationHandler {

    private IPeople target;

    private PeopleProxyHandler() {
    }

    public static IPeople newProxyInstance(IPeople target) {
        PeopleProxyHandler proxy = new PeopleProxyHandler();
        proxy.target = target;
        return (IPeople) Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(), proxy);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        Object ret = null;
        try {
            ret = method.invoke(target, args);
            System.out.println("加个鸡腿");
        } catch (Exception e) {
            System.err.println("PeopleDynamicProxy 执行业务方法异常");
        }
        return ret;
    }
}
