package com.kevinyin.algorithm.tree.binary;

/**
 * @author yinhao
 * @Description TODO
 * @Date 2021/11/24 17:20
 */
public class BinaryTreeRoot {

    private BinaryTreeNode root;

    public BinaryTreeNode getRoot() {
        return root;
    }

    public void insert(int k, String v) {
        BinaryTreeNode node = new BinaryTreeNode();
        node.k = k;
        node.v = v;
        if (root == null) {
            root = node;
            return;
        }
        BinaryTreeNode current = root;
        while (true) {
            if (node.k < current.k) {
                BinaryTreeNode leftNode = current.leftNode;
                if (leftNode == null) {
                    current.leftNode = node;
                    return;
                }
                current = leftNode;
            } else {
                BinaryTreeNode rightNode = current.rightNode;
                if (rightNode == null) {
                    current.rightNode = node;
                    return;
                }
                current = rightNode;
            }
        }
    }


    public String query(int k) {
        if (root == null) {
            return null;
        }
        BinaryTreeNode current = root;
        while (true) {
            if (k == current.k) {
                return current.v;
            } else if (k < current.k) {
                BinaryTreeNode leftNode = current.leftNode;
                if (leftNode == null) {
                    return null;
                }
                current = leftNode;
            } else {
                BinaryTreeNode rightNode = current.rightNode;
                if (rightNode == null) {
                    return null;
                }
                current = rightNode;
            }
        }
    }

}
