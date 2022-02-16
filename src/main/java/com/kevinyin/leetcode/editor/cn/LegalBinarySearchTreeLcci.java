//实现一个函数，检查一棵二叉树是否为二叉搜索树。示例 1: 输入:     2    / \   1   3 输出: true 示例 2: 输入:     5
//    / \   1   4      / \     3   6 输出: false 解释: 输入为: [5,1,4,null,null,3,6]。    
//  根节点的值为 5 ，但是其右子节点值为 4 。 Related Topics 树 深度优先搜索 二叉搜索树 二叉树 👍 68 👎 0


package com.kevinyin.leetcode.editor.cn;

import java.util.Stack;

public class LegalBinarySearchTreeLcci {
    public static void main(String[] args) {
        Solution solution = new LegalBinarySearchTreeLcci().new Solution();
        TreeNode node = new TreeNode(10);
        TreeNode n1 = new TreeNode(5);
        TreeNode n2 = new TreeNode(15);
        TreeNode n3 = new TreeNode(6);
        TreeNode n4 = new TreeNode(20);
        node.left = n1;
        node.right = n2;
        n2.left = n3;
        n2.right = n4;
        solution.isValidBST(node);
    }
    //leetcode submit region begin(Prohibit modification and deletion)

    /**
     * Definition for a binary tree node.
     * public class TreeNode {
     * int val;
     * TreeNode left;
     * TreeNode right;
     * TreeNode(int x) { val = x; }
     * }
     */
    class Solution {

        public boolean isValidBST(TreeNode root) {
            if (root == null) {
                return true;
            }
            Stack<TreeNode> stack = new Stack<>();
            TreeNode pre = null;
            while (root != null || !stack.isEmpty()) {
                while (root != null) {
                    stack.push(root);
                    root = root.left;
                }
                root = stack.pop();
                if (pre != null && root.val <= pre.val) {
                    return false;
                }
                pre = root;
                root = root.right;
            }
            return true;
        }

    }
//leetcode submit region end(Prohibit modification and deletion)

    class Solution1 {
        public boolean isValidBST(TreeNode root) {
            if (root == null || (root.right == null && root.left == null)) {
                return true;
            }
            return isValidBST(root, Long.MIN_VALUE, Long.MAX_VALUE);
        }

        private boolean isValidBST(TreeNode node, long min, long max) {
            if (node == null) {
                return true;
            }
            if (node.val >= max || node.val <= min) {
                return false;
            }
            if (!isValidBST(node.left, min, node.val)) {
                return false;
            }
            if (!isValidBST(node.right, node.val, max)) {
                return false;
            }
            return true;
        }
    }

    class Solution2 {
        TreeNode pre;

        public boolean isValidBST(TreeNode root) {
            if (root == null) {
                return true;
            }
            if (!isValidBST(root.left)) {
                return false;
            }
            if (pre != null && pre.val >= root.val) {
                return false;
            }
            pre = root;
            if (!isValidBST(root.right)) {
                return false;
            }
            return true;
        }

    }
}