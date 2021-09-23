package com.kevinyin.proxy.jdk;

public class MyMainTest {

    public static void main(String[] args) {
        IPeople people = new WangPeople();
        PeopleProxy proxy = new PeopleProxy(people);
        proxy.eat();
    }
}
