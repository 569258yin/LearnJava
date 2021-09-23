package com.kevinyin.proxy.cglib;

import com.kevinyin.proxy.jdk.WangPeople;
import net.sf.cglib.core.DebuggingClassWriter;
import net.sf.cglib.proxy.Enhancer;

public class CglibMainTest {

    public static void main(String[] args) {
        System.setProperty(DebuggingClassWriter.DEBUG_LOCATION_PROPERTY, "xxx/code");
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(WangPeople.class);
        enhancer.setCallback(new PeopleMethodInterceptor());
        WangPeople people = (WangPeople) enhancer.create();
        people.eat();
    }
}
