package com.kevinyin.proxy.jdk;

public class JDKMainTest {

    public static void main(String[] args) {
        //动态代理时生成class文件
        System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles", "true");
        IPeople people = new WangPeople();
        IPeople wangPeople = PeopleProxyHandler.newProxyInstance(people);
        wangPeople.eat();
    }
}
