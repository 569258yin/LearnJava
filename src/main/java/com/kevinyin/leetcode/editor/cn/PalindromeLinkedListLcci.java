//编写一个函数，检查输入的链表是否是回文的。
//
// 
//
// 示例 1： 
//
// 输入： 1->2
//输出： false 
// 
//
// 示例 2： 
//
// 输入： 1->2->2->1
//输出： true 
// 
//
// 
//
// 进阶： 
//你能否用 O(n) 时间复杂度和 O(1) 空间复杂度解决此题？ 
// Related Topics 栈 递归 链表 双指针 👍 92 👎 0


package com.kevinyin.leetcode.editor.cn;

import java.util.Stack;

public class PalindromeLinkedListLcci {
    public static void main(String[] args) {
        Solution solution = new PalindromeLinkedListLcci().new Solution();
    }
    //leetcode submit region begin(Prohibit modification and deletion)

    /**
     * Definition for singly-linked list.
     * public class ListNode {
     * int val;
     * ListNode next;
     * ListNode(int x) { val = x; }
     * }
     */
    class Solution {
        public boolean isPalindrome(ListNode head) {
            ListNode slow = head;
            ListNode fast = head;
            ListNode pre = null;
            while (fast != null && fast.next != null) {
                ListNode oldCur = slow;
                slow = slow.next;
                fast = fast.next.next;
                oldCur.next = pre;
                pre = oldCur;
            }
            if (fast != null) {
                slow = slow.next;
            }
            while (slow != null) {
                if (slow.val != pre.val){
                    return false;
                }
                slow = slow.next;
                pre = pre.next;
            }
            return true;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}