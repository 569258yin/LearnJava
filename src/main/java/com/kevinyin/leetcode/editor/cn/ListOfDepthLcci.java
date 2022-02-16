//给定一棵二叉树，设计一个算法，创建含有某一深度上所有节点的链表（比如，若一棵树的深度为 D，则会创建出 D 个链表）。返回一个包含所有深度的链表的数组。
//
// 
//
// 示例： 
//
// 输入：[1,2,3,4,5,null,7,8]
//
//        1
//       /  \ 
//      2    3
//     / \    \ 
//    4   5    7
//   /
//  8
//
//输出：[[1],[2,3],[4,5,7],[8]]
// 
// Related Topics 树 广度优先搜索 链表 二叉树 👍 65 👎 0


package com.kevinyin.leetcode.editor.cn;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class ListOfDepthLcci {
    public static void main(String[] args) {
        Solution solution = new ListOfDepthLcci().new Solution();
    }
    //leetcode submit region begin(Prohibit modification and deletion)
/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode(int x) { val = x; }
 * }
 */
    /**
     * Definition for singly-linked list.
     * public class ListNode {
     * int val;
     * ListNode next;
     * ListNode(int x) { val = x; }
     * }
     */
    class Solution {

        public ListNode[] listOfDepth(TreeNode tree) {
            List<ListNode> listNodes = new ArrayList<>();
            dfs(tree, listNodes, 1);
            return listNodes.toArray(new ListNode[0]);
        }

        private void dfs(TreeNode node, List<ListNode> listNodes, int deep) {
            if (node == null) {
                return;
            }
            if (deep > listNodes.size()) {
                listNodes.add(new ListNode(node.val));
            } else {
                ListNode n = listNodes.get(deep - 1);
                while (n.next != null) { n = n.next; }
                n.next = new ListNode(node.val);
            }
            dfs(node.left, listNodes, deep + 1);
            dfs(node.right, listNodes, deep + 1);
        }


    }
//leetcode submit region end(Prohibit modification and deletion)

    public ListNode[] listOfDepth(TreeNode tree) {
        List<ListNode> listNodes = new ArrayList<>();
        Deque<TreeNode> deque = new ArrayDeque<>();
        deque.offer(tree);
        while (!deque.isEmpty()) {
            int size = deque.size();
            ListNode listNode = new ListNode(0);
            ListNode cur = listNode;
            for (int i = 0; i < size; i++) {
                TreeNode treeNode = deque.poll();
                cur.next = new ListNode(treeNode.val);
                cur = cur.next;

                if (treeNode.left != null) {
                    deque.offer(treeNode.left);
                }
                if (treeNode.right != null) {
                    deque.offer(treeNode.right);
                }
            }
            listNodes.add(listNode.next);
        }
        return listNodes.toArray(new ListNode[0]);
    }
}