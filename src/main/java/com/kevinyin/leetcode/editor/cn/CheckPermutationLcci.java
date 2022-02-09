//给定两个字符串 s1 和 s2，请编写一个程序，确定其中一个字符串的字符重新排列后，能否变成另一个字符串。
//
// 示例 1： 
//
// 输入: s1 = "abc", s2 = "bca"
//输出: true 
// 
//
// 示例 2： 
//
// 输入: s1 = "abc", s2 = "bad"
//输出: false
// 
//
// 说明： 
//
// 
// 0 <= len(s1) <= 100 
// 0 <= len(s2) <= 100 
// 
// Related Topics 哈希表 字符串 排序 👍 57 👎 0


package com.kevinyin.leetcode.editor.cn;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class CheckPermutationLcci {
    public static void main(String[] args) {
        Solution solution = new CheckPermutationLcci().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public boolean CheckPermutation(String s1, String s2) {
            if (s1.length() != s2.length()) {
                return false;
            }
            Map<Character, Integer> map1 = new HashMap<>();
            for (char c : s1.toCharArray()) {
                map1.put(c, map1.getOrDefault(c, 0) + 1);
            }
            for (char c : s2.toCharArray()) {
                if (!map1.containsKey(c) || map1.get(c) <= 0) {
                    return false;
                }
                map1.put(c, map1.get(c) - 1);
            }
            return true;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}