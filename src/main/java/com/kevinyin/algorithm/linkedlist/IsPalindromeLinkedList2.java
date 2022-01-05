package com.kevinyin.algorithm.linkedlist;

/**
 * https://leetcode-cn.com/problems/palindrome-linked-list/
 * 给你一个单链表的头节点 head ，请你判断该链表是否为回文链表。如果是，返回 true ；否则，返回 false 。
 *
 * @author yinhao
 * @Description TODO
 * @Date 2022/1/4 16:58
 */
public class IsPalindromeLinkedList2 {

    public static void main(String[] args) {
        ListNode head = new ListNode(1);
        ListNode node2 = new ListNode(2);
        ListNode node3 = new ListNode(3);
        ListNode node4 = new ListNode(1);
        head.next = node2;
        node2.next = node3;
        node3.next = node4;
        System.out.println(new IsPalindromeLinkedList2().isPalindrome(head));
    }

    public boolean isPalindrome(ListNode head) {
        int length = 0;
        ListNode c = head;
        while (c != null) {
            length++;
            c = c.next;
        }
        int[] array = new int[length];
        int i = 0;
        while (head != null) {
            array[i] = head.val;
            i++;
            head = head.next;
        }
        for (int j = 0, k = array.length - 1; j < array.length / 2; j++,k--) {
            if (array[j] != array[k]) {
                return false;
            }
        }
        return true;
    }


}
