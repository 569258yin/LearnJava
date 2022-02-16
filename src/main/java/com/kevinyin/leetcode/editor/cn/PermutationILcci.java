//无重复字符串的排列组合。编写一种方法，计算某字符串的所有排列组合，字符串每个字符均不相同。
//
// 示例1: 
//
// 
// 输入：S = "qwe"
// 输出：["qwe", "qew", "wqe", "weq", "ewq", "eqw"]
// 
//
// 示例2: 
//
// 
// 输入：S = "ab"
// 输出：["ab", "ba"]
// 
//
// 提示: 
//
// 
// 字符都是英文字母。 
// 字符串长度在[1, 9]之间。 
// 
// Related Topics 字符串 回溯 👍 59 👎 0


package com.kevinyin.leetcode.editor.cn;

import java.util.ArrayList;
import java.util.List;

public class PermutationILcci {
    public static void main(String[] args) {
        Solution solution = new PermutationILcci().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        List<String> list = new ArrayList<>();

        public String[] permutation(String s) {
            int n = s.length();
            // 标记是否已经访问过了，以便于下次深度搜索不允许访问
            int[] flag = new int[n];
            // 已访问的字符记录到临时数组中
            char[] temp_arr = new char[n];
            dfs(0, flag, temp_arr, s);
            return list.toArray(new String[0]);
        }

        public void dfs(int step, int[] flag, char[] arr, String s) {
            // 当深度搜索到达了满足的字符串长度后，就可以进行拼接返回了
            if (step >= s.length()) {
                list.add(new String(arr));
                return;
            }
            for (int i = 0; i < s.length(); i++) {
                if (flag[i] == 0) {
                    arr[step] = s.charAt(i);
                    flag[i] = 1;
                    dfs(step + 1, flag, arr, s);
                    flag[i] = 0;
                }
            }
        }

    }
//leetcode submit region end(Prohibit modification and deletion)

}