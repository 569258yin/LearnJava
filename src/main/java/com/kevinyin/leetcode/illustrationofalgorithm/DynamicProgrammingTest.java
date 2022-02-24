package com.kevinyin.leetcode.illustrationofalgorithm;

/**
 * 递归
 */
public class DynamicProgrammingTest {

    /**
     * 小试牛刀：三角形的最小路径和
     */
    static class Solution1 {

        private static final int[][] triangle = {{2, 0, 0, 0}, {3, 4, 0, 0}, {6, 5, 7, 0}, {4, 1, 8, 3}};

        public static void main(String[] args) throws Throwable {
            int minPathSum = fun();
            System.out.println("sum = " + minPathSum);
        }

        /**
         * 找出最小的路径和
         */
        public static int fun() {
            int maxRow = triangle.length;
            int[] mini = triangle[maxRow - 1];
            for (int i = maxRow - 2; i >= 0; i--) {
                for (int j = 0; j < triangle[i].length; j++) {
                    if (triangle[i][j] == 0) {
                        break;
                    }
                    mini[j] = triangle[i][j] + Math.min(mini[j], mini[j + 1]);
                }
            }
            return mini[0];
        }
    }

    /**
     * 给定不同面额的硬币 coins 和一个总金额 amount。编写一个函数来计算可以凑成总金额所需的最少的硬币个数。
     * 如果没有任何一种硬币组合能组成总金额，返回 -1。
     * 输入: coins = [1, 2, 5], amount = 11，输出: 3  解释: 11 = 5 + 5 + 1
     * 输入: coins = [2], amount = 3，输出: -1
     */
    static class Solution2 {

        private static int exchange(int amount, int[] coins) {
            int[] dp = new int[amount + 1];
            for (int i = 0; i < amount + 1; i++) {
                dp[i] = Integer.MAX_VALUE;
            }
            dp[0] = 0;
            for (int i = 0; i < amount + 1; i++) {
                for (int j = 0; j < coins.length; j++) {
                    if (i >= coins[j]) {
                        dp[i] = Math.min(dp[i - coins[j]], dp[i]) + 1;
                    }
                }
            }
            if (dp[amount] == dp[amount + 1]) {
                return -1;
            }
            return dp[amount];
        }
    }

}
