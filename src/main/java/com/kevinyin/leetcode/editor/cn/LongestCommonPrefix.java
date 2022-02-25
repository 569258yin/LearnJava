//编写一个函数来查找字符串数组中的最长公共前缀。
//
// 如果不存在公共前缀，返回空字符串 ""。 
//
// 
//
// 示例 1： 
//
// 
//输入：strs = ["flower","flow","flight"]
//输出："fl"
// 
//
// 示例 2： 
//
// 
//输入：strs = ["dog","racecar","car"]
//输出：""
//解释：输入不存在公共前缀。 
//
// 
//
// 提示： 
//
// 
// 1 <= strs.length <= 200 
// 0 <= strs[i].length <= 200 
// strs[i] 仅由小写英文字母组成 
// 
// Related Topics 字符串 👍 2044 👎 0


package com.kevinyin.leetcode.editor.cn;

public class LongestCommonPrefix {
    public static void main(String[] args) {
        Solution solution = new LongestCommonPrefix().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public String longestCommonPrefix(String[] strs) {
            if (strs == null || strs.length == 0) {
                return "";
            }
            String ans = strs[0];
            for (int i = 1; i < strs.length; i++) {
                ans = longestCommonPrefix(ans, strs[i]);
                if (ans.length() == 0) {
                    break;
                }
            }
            return ans;
        }

        public String longestCommonPrefix(String s1, String s2) {
            int length = Math.min(s1.length(), s2.length());
            int index = 0;
            while (index < length && s1.charAt(index) == s2.charAt(index)) {
                index++;
            }
            return s1.substring(0, index);
        }

    }
//leetcode submit region end(Prohibit modification and deletion)

}