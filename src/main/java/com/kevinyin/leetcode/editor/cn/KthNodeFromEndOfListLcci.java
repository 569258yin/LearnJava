//实现一种算法，找出单向链表中倒数第 k 个节点。返回该节点的值。
//
// 注意：本题相对原题稍作改动 
//
// 示例： 
//
// 输入： 1->2->3->4->5 和 k = 2
//输出： 4 
//
// 说明： 
//
// 给定的 k 保证是有效的。 
// Related Topics 链表 双指针 👍 91 👎 0


package com.kevinyin.leetcode.editor.cn;

import java.util.ArrayList;
import java.util.List;

public class KthNodeFromEndOfListLcci {
    public static void main(String[] args) {
        Solution solution = new KthNodeFromEndOfListLcci().new Solution();
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
        int size;

        public int kthToLast(ListNode head, int k) {
            if (head == null) {
                return 0;
            }
            int value = kthToLast(head.next, k);
            if (++size == k) {
                return head.val;
            }
            return value;
        }

    }
//leetcode submit region end(Prohibit modification and deletion)


    class MySolution {
        public int kthToLast(ListNode head, int k) {
            List<Integer> a = new ArrayList<>();
            while (head != null) {
                a.add(head.val);
                head = head.next;
            }
            return a.get(a.size() - k);
        }

    }

    public static class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }
}