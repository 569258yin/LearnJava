//给你一个字符串 s，找到 s 中最长的回文子串。
//
// 
//
// 示例 1： 
//
// 
//输入：s = "babad"
//输出："bab"
//解释："aba" 同样是符合题意的答案。
// 
//
// 示例 2： 
//
// 
//输入：s = "cbbd"
//输出："bb"
// 
//
// 示例 3： 
//
// 
//输入：s = "a"
//输出："a"
// 
//
// 示例 4： 
//
// 
//输入：s = "ac"
//输出："a"
// 
//
// 
//
// 提示： 
//
// 
// 1 <= s.length <= 1000 
// s 仅由数字和英文字母（大写和/或小写）组成 
// 
// Related Topics 字符串 动态规划 👍 4664 👎 0


package com.kevinyin.leetcode.editor.cn;

public class LongestPalindromicSubstring {
    public static void main(String[] args) {
        Solution solution = new LongestPalindromicSubstring().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public String longestPalindrome(String s) {
            if (s.equals(""))
                return "";
            String origin = s;
            String reverse = new StringBuffer(s).reverse().toString(); //字符串倒置
            int length = s.length();
            int[][] arr = new int[length][length];
            int maxLen = 0;
            int maxEnd = 0;
            for (int i = 0; i < length; i++)
                for (int j = 0; j < length; j++) {
                    if (origin.charAt(i) == reverse.charAt(j)) {
                        if (i == 0 || j == 0) {
                            arr[i][j] = 1;
                        } else {
                            arr[i][j] = arr[i - 1][j - 1] + 1;
                        }
                    }
                    if (arr[i][j] > maxLen) {
                        maxLen = arr[i][j];
                        maxEnd = i; //以 i 位置结尾的字符
                    }

                }
            return s.substring(maxEnd - maxLen + 1, maxEnd + 1);
        }


        /**
         * 判断是否为回文字符串
         * 从头和尾开始对比
         *
         * @param s
         * @return
         */
        private boolean isPalindromic(String s) {
            int len = s.length();
            for (int i = 0; i < len / 2; i++) {
                if (s.charAt(i) != s.charAt(len - i - 1)) {
                    return false;
                }
            }
            return true;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}