package com.kevinyin.leetcode.editor.cn;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class ListNode {
    public int val;
    public ListNode next;

    ListNode() {
    }

    ListNode(int val) {
        this.val = val;
    }

    ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        ListNode node = (ListNode) o;

        return new EqualsBuilder().append(val, node.val).append(next, node.next).isEquals();
    }

    @Override
    public int hashCode() {
        int nextValue = next != null ? next.val : 0;
        return new HashCodeBuilder(17, 37).append(val).append(nextValue).toHashCode();
    }

    @Override
    public String toString() {
        return "ListNode{" +
                "val=" + val +
                '}';
    }

    public static ListNode buildByArray(int[] array) {
        ListNode node = new ListNode(array[0]);
        ListNode pre = node;
        for (int i = 1; i < array.length; i++) {
            pre.next = new ListNode(array[i]);
            pre = pre.next;
        }
        return node;
    }

}
