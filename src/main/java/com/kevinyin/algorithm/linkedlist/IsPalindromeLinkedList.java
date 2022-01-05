package com.kevinyin.algorithm.linkedlist;

/**
 * https://leetcode-cn.com/problems/palindrome-linked-list/
 * 给你一个单链表的头节点 head ，请你判断该链表是否为回文链表。如果是，返回 true ；否则，返回 false 。
 *
 * @author yinhao
 * @Description TODO
 * @Date 2022/1/4 16:58
 */
public class IsPalindromeLinkedList {

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(2);
        ListNode node4 = new ListNode(1);
        head.next = node2;
        node2.next = node3;
        node3.next = node4;
        System.out.println(new IsPalindromeLinkedList().isPalindrome(head));
    }

    private ListNode frontPoint;

    public boolean isPalindrome(ListNode head) {
        frontPoint = head;
        return recursivelyCheck(head);
    }

    public boolean recursivelyCheck(ListNode currentNode) {
        if (currentNode != null) {
            boolean flag = recursivelyCheck(currentNode.next);
            if (!flag) {
                return false;
            }
            if (currentNode.val != frontPoint.val) {
                return false;
            }
            frontPoint = frontPoint.next;
        }
        return true;
    }


}
