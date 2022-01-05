package com.kevinyin.algorithm.linkedlist;

import lombok.Data;

/**
 * @author yinhao
 * @Description TODO
 * @Date 2022/1/4 16:58
 */
@Data
public class ListNode {
    int val;
    ListNode next;

    ListNode() {
    }

    ListNode(int val) {
        this.val = val;
    }

    ListNode(int val, ListNode next) {
        this.val = val;
        this.next = next;
    }
}
