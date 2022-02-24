package com.kevinyin.leetcode.illustrationofalgorithm;

public class RecursionTest {


    public static void main(String[] args) {
        System.out.println(new Solution1().factorial(2));
    }

    /**
     * 求阶层
     * 1
     */
    static class Solution1 {

        public int factorial(int i) {
            if (i <= 1) {
                return 1;
            }
            return i * factorial(i - 1);
        }
    }

    /**
     * 青蛙跳台阶
     * 1
     */
    static class Solution2 {
        public int fun(int n) {
            if (n == 1) {
                return 1;
            }
            if (n == 2) {
                return 2;
            }
            int result = 0;
            int pre = 1;
            int next = 2;
            for (int i = 3; i <= n; i++) {
                result = pre + next;
                pre = next;
                next = result;
            }
            return result;
        }
    }

    /**
     * 汉诺塔
     */
    static class Solution3 {

        // 将 n 个圆盘从 a 经由 b 移动到 c 上
        public void hanoid(int n, char a, char b, char c) {
            if (n <= 0) {
                return;
            }
            hanoid(n - 1, a, c, b);
            move(a, c);
            hanoid(n - 1, b, a, c);
        }

        public void move(char a, char b) {
            System.out.printf("%c->%c\n", a, b);
        }
    }

    /**
     * 给定不同面额的硬币 coins 和一个总金额 amount。编写一个函数来计算可以凑成总金额所需的最少的硬币个数。
     * 如果没有任何一种硬币组合能组成总金额，返回 -1。
     * 输入: coins = [1, 2, 5], amount = 11，输出: 3  解释: 11 = 5 + 5 + 1
     * 输入: coins = [2], amount = 3，输出: -1
     */
    static class Solution4 {

        private static int exchange(int amount, int[] coins) {
            if (amount == 0) {
                return 0;
            }
            if (amount < 0) {
                return -1;
            }
            int result = Integer.MAX_VALUE;
            for (int i = 0; i < coins.length; i++) {
                int subMin = exchange(amount - coins[i], coins);
                if (subMin == -1) {
                    continue;
                }
                result = Math.min(result, subMin + 1);
            }
            if (result == Integer.MAX_VALUE) {
                return -1;
            }
            return result;
        }

        public static  void main(String[] args)  throws Throwable {
            int amount = 11;
            int[] coins = {1,2,5};
            int result = exchange(amount, coins);
            System.out.println("result = " + result);
        }
    }


}
