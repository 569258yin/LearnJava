package com.kevinyin.algorithm.matht;

/**
 * https://leetcode-cn.com/problems/count-primes/
 * 统计所有小于非负整数 n 的质数的数量
 *
 * @author yinhao
 * @Description TODO
 * @Date 2022/1/5 17:32
 */
public class CountPrimes {


    public static void main(String[] args) {
        int count = new CountPrimes().countPrimes(10);
        System.out.println(count);
    }


    public int countPrimes(int n) {
        if (n <= 1) {
            return 0;
        }
        if (n == 2) {
            return 1;
        }
        int count = 1;
        for (int i = 3; i < n; i++) {
            if (checkPrime(i)) {
                count++;
            }
        }
        return count;
    }

    public boolean checkPrime(int num) {
        int ceil = (int) Math.sqrt(num);
        if (ceil < 2) {
            return true;
        }
        for (int i = 2; i <= ceil; i++) {
            if (num % i == 0) {
                return false;
            }
        }
        return true;
    }
}
