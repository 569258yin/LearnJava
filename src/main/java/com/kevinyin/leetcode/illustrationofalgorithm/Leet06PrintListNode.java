package com.kevinyin.leetcode.illustrationofalgorithm;

import java.util.ArrayDeque;
import java.util.Queue;

public class Leet06PrintListNode {

    public static void main(String[] args) {
        ListNode list1 = new ListNode(1);
        ListNode list2 = new ListNode(3);
        ListNode list3 = new ListNode(2);
        list1.next = list2;
        list2.next = list3;
        int[] array = mainReversePrint(list1);
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i] + ",");
        }
    }


    public static int[] mainReversePrint(ListNode head) {
        Queue<Integer> queue = new ArrayDeque<>();
        while (head.next != null) {
            queue.add(head.val);
            head = head.next;
        }
        int[] array = new int[queue.size()];
        int i = 0;
        while (true) {
            Integer v = queue.poll();
            if (v == null) {
                break;
            }
            array[i++] = v;
        }
        return array;
    }

    public static int[] reversePrint(ListNode head) {
        int count = 0;
        ListNode c = head;
        while (c.next != null) {
            count++;
            c = c.next;
        }
        int[] array = new int[count];
        count--;
        while (head != null && count > 0) {
            array[count--] = head.val;
            head = head.next;
        }
        return array;
    }

    public static class ListNode {
        int val;
        ListNode next;

        ListNode(int x) {
            val = x;
        }
    }

}
