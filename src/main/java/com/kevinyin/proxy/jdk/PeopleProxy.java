package com.kevinyin.proxy.jdk;

public class PeopleProxy implements IPeople {

    private IPeople peopleManager;

    public PeopleProxy(IPeople peopleManager) {
        this.peopleManager = peopleManager;
    }

    @Override
    public void eat() {
        peopleManager.eat();
        System.out.println("加个鸡腿");
    }
}
