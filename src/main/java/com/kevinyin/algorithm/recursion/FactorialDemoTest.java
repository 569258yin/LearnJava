package com.kevinyin.algorithm.recursion;

public class FactorialDemoTest {

    public static void main(String[] args) {
        System.out.println(fact(5));
    }

    public static int fact(int x) {
        if (x == 1) {
            return 1;
        }
        return x * fact(x - 1);
    }
}
