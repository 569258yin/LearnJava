//设计并实现一个算法，找出二叉树中某两个节点的第一个共同祖先。不得将其他的节点存储在另外的数据结构中。注意：这不一定是二叉搜索树。
//
// 例如，给定如下二叉树: root = [3,5,1,6,2,0,8,null,null,7,4] 
//
//     3
//   / \
//  5   1
// / \ / \
//6  2 0  8
//  / \
// 7   4
// 
//
// 示例 1: 
//
// 输入: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 1
//输出: 3
//解释: 节点 5 和节点 1 的最近公共祖先是节点 3。 
//
// 示例 2: 
//
// 输入: root = [3,5,1,6,2,0,8,null,null,7,4], p = 5, q = 4
//输出: 5
//解释: 节点 5 和节点 4 的最近公共祖先是节点 5。因为根据定义最近公共祖先节点可以为节点本身。 
//
// 说明: 
//
// 所有节点的值都是唯一的。
//p、q 为不同节点且均存在于给定的二叉树中。 
// Related Topics 树 深度优先搜索 二叉树 👍 67 👎 0


package com.kevinyin.leetcode.editor.cn;

public class FirstCommonAncestorLcci {
    public static void main(String[] args) {
        Solution solution = new FirstCommonAncestorLcci().new Solution();
        TreeNode node = new TreeNode(3);
        TreeNode n1 = new TreeNode(5);
        TreeNode n2 = new TreeNode(1);
        TreeNode n3 = new TreeNode(6);
        TreeNode n4 = new TreeNode(2);
        TreeNode n5 = new TreeNode(0);
        TreeNode n6 = new TreeNode(8);
        TreeNode n7 = new TreeNode(7);
        TreeNode n8 = new TreeNode(4);
        node.left = n1;
        node.right = n2;
        n1.left = n3;
        n1.right = n4;
        n2.left = n5;
        n2.right = n6;
        n4.left = n7;
        n4.right = n8;
        solution.lowestCommonAncestor(node, n1, n2);
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

        TreeNode ans;

        public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
            dfs(root, p, q);
            return ans;
        }

        public boolean dfs(TreeNode node, TreeNode p, TreeNode q) {
            if (node == null) {
                return false;
            }
            boolean left = dfs(node.left, p, q);
            boolean right = dfs(node.right, p, q);
            if (node.equals(p) || node.equals(q)) {
                // 在之前找到的基础上，如果又找到了一个，那就是当前节点了
                if (left || right) {
                    ans = node;
                    return false;
                } else {
                    return true;
                }
            } else {
                // 刚好在左边和右边都找到了，就是公共的根节点了
                if (left && right) {
                    ans = node;
                    return false;
                }
            }
            return left || right;
        }
    }
//leetcode submit region end(Prohibit modification and deletion)

}