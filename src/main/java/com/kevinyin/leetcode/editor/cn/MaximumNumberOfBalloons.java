//给你一个字符串 text，你需要使用 text 中的字母来拼凑尽可能多的单词 "balloon"（气球）。
//
// 字符串 text 中的每个字母最多只能被使用一次。请你返回最多可以拼凑出多少个单词 "balloon"。 
//
// 
//
// 示例 1： 
//
// 
//
// 输入：text = "nlaebolko"
//输出：1
// 
//
// 示例 2： 
//
// 
//
// 输入：text = "loonbalxballpoon"
//输出：2
// 
//
// 示例 3： 
//
// 输入：text = "leetcode"
//输出：0
// 
//
// 
//
// 提示： 
//
// 
// 1 <= text.length <= 10^4 
// text 全部由小写英文字母组成 
// 
// Related Topics 哈希表 字符串 计数 👍 97 👎 0


package com.kevinyin.leetcode.editor.cn;

import java.util.HashMap;
import java.util.Map;

public class MaximumNumberOfBalloons {
    public static void main(String[] args) {
        Solution solution = new MaximumNumberOfBalloons().new Solution();
    }

    //leetcode submit region begin(Prohibit modification and deletion)
    class Solution {
        public int maxNumberOfBalloons(String text) {
            Map<Character, Integer> charMap = new HashMap<>();
            for (char c : text.toCharArray()) {
                charMap.put(c, charMap.getOrDefault(c, 0) + 1);
            }
            String mode = "balloon";
            int total = 0;
            while (true) {
                boolean un = false;
                for (char c : mode.toCharArray()) {
                    int times = charMap.getOrDefault(c, 0);
                    if (times <= 0) {
                        un = true;
                        break;
                    }
                    charMap.put(c, --times);
                }
                if (un) {
                    break;
                }
                total++;
            }
            return total;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}