package com.kevinyin.algorithm.tree.binary;

/**
 * @author yinhao
 * @Description TODO
 * @Date 2021/11/25 16:51
 */
public class TestMain {

    public static void main(String[] args) {
        BinaryTreeRoot root = new BinaryTreeRoot();
        root.insert(1, "kevin");
        root.insert(11, "Bob");
        root.insert(15, "Tom");
        root.insert(120, "Lili");
        root.insert(3, "Tiny");
        root.insert(29, "Mary");
        root.insert(18, "Cat");
        root.insert(109, "Lory");
        TreeNodeShow.show(root.getRoot());
    }
}
