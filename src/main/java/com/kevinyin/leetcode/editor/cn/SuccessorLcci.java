//设计一个算法，找出二叉搜索树中指定节点的“下一个”节点（也即中序后继）。
//
// 如果指定节点没有对应的“下一个”节点，则返回null。 
//
// 示例 1: 
//
// 输入: root = [2,1,3], p = 1
//
//  2
// / \
//1   3
//
//输出: 2 
//
// 示例 2: 
//
// 输入: root = [5,3,6,2,4,null,null,1], p = 6
//
//      5
//     / \
//    3   6
//   / \
//  2   4
// /   
//1
//
//输出: null 
// Related Topics 树 深度优先搜索 二叉搜索树 二叉树 👍 88 👎 0


package com.kevinyin.leetcode.editor.cn;

public class SuccessorLcci {
    public static void main(String[] args) {
        Solution solution = new SuccessorLcci().new Solution();
        TreeNode node = new TreeNode(10);
        TreeNode n1 = new TreeNode(5);
        TreeNode n2 = new TreeNode(15);
        TreeNode n3 = new TreeNode(6);
        TreeNode n4 = new TreeNode(20);
        node.left = n1;
        node.right = n2;
        n2.left = n3;
        n2.right = n4;
        System.out.println(solution.inorderSuccessor(node, n3));
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
        TreeNode pre;

        public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
            if (root == null) {
                return null;
            }
            TreeNode result = inorderSuccessor(root.left, p);
            if (result != null) {
                return result;
            }
            if (p.equals(pre)) {
                return root;
            }
            pre = root;
            return inorderSuccessor(root.right, p);
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}