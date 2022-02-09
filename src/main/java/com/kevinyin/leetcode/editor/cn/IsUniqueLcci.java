//实现一个算法，确定一个字符串 s 的所有字符是否全都不同。
//
// 示例 1： 
//
// 输入: s = "leetcode"
//输出: false 
// 
//
// 示例 2： 
//
// 输入: s = "abc"
//输出: true
// 
//
// 限制： 
// 
// 0 <= len(s) <= 100 
// 如果你不使用额外的数据结构，会很加分。 
// 
// Related Topics 位运算 哈希表 字符串 排序 👍 184 👎 0


package com.kevinyin.leetcode.editor.cn;

import java.util.HashSet;
import java.util.Set;

public class IsUniqueLcci {
    public static void main(String[] args) {
        Solution solution = new IsUniqueLcci().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public boolean isUnique(String astr) {
            Set<Character> set = new HashSet<>();
            for (char c : astr.toCharArray()) {
                if (!set.add(c)) {
                    return false;
                }
            }
            return true;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}