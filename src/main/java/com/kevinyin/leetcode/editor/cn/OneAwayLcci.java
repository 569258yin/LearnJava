//字符串有三种编辑操作:插入一个字符、删除一个字符或者替换一个字符。 给定两个字符串，编写一个函数判定它们是否只需要一次(或者零次)编辑。
//
// 
//
// 示例 1: 
//
// 输入: 
//first = "pale"
//second = "ple"
//输出: True 
//
// 
//
// 示例 2: 
//
// 输入: 
//first = "pales"
//second = "pal"
//输出: False
// 
// Related Topics 双指针 字符串 👍 105 👎 0


package com.kevinyin.leetcode.editor.cn;

public class OneAwayLcci {
    public static void main(String[] args) {
        Solution solution = new OneAwayLcci().new Solution();
        solution.oneEditAway("", "s");
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public boolean oneEditAway(String first, String second) {
            if (Math.abs(first.length() - second.length()) > 1) {
                return false;
            }
            int diff = 0;
            int xPoint = 0;
            int yPoint = 0;
            if (first.length() == second.length()) {
                while (xPoint < first.length() && yPoint < second.length()) {
                    if (first.charAt(xPoint) != second.charAt(yPoint)) {
                        if (++diff > 1) {
                            return false;
                        }
                    }
                    xPoint++;
                    yPoint++;
                }
                return true;
            } else {
                if (first.length() == 0 || second.length() == 0) {
                    return true;
                }
                while (diff <= 1) {
                    if (first.charAt(xPoint) == second.charAt(yPoint)) {
                        xPoint++;
                        yPoint++;
                    } else {
                        diff++;
                        if (first.length() > second.length()) {
                            xPoint++;
                        } else {
                            yPoint++;
                        }
                    }
                    if (xPoint >= first.length() || yPoint >= second.length()) {
                        if (diff <= 1) {
                            return true;
                        }
                        return false;
                    }
                }
                return false;
            }
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}