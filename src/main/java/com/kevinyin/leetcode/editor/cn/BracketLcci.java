//括号。设计一种算法，打印n对括号的所有合法的（例如，开闭一一对应）组合。
//
// 说明：解集不能包含重复的子集。 
//
// 例如，给出 n = 3，生成结果为： 
//
// 
//[
//  "((()))",
//  "(()())",
//  "(())()",
//  "()(())",
//  "()()()"
//]
// 
// Related Topics 字符串 动态规划 回溯 👍 101 👎 0


package com.kevinyin.leetcode.editor.cn;

import java.util.ArrayList;
import java.util.List;

public class BracketLcci {
    public static void main(String[] args) {
        Solution solution = new BracketLcci().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public List<String> generateParenthesis(int n) {
            List<String> list = new ArrayList<>();
            dfs(n, 0, 0, new StringBuffer(), list);
            return list;
        }


        public void dfs(int n, int leftNum, int rightNum, StringBuffer stringBuffer, List<String> list) {
            if (leftNum < rightNum || leftNum > n) {
                return;
            }
            if (stringBuffer.length() == n * 2) {
                list.add(stringBuffer.toString());
                return;
            }
            dfs(n, leftNum + 1, rightNum, stringBuffer.append("("), list);

            dfs(n, leftNum, rightNum + 1, stringBuffer.append(")"), list);
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}