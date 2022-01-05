package com.kevinyin.datastructure.linkedlist;

/**
 * @author yinhao
 * @Description TODO
 * @Date 2021/12/28 15:28
 */
public class MyLinkedList {
    /**
     * 头节点
     */
    private Node head;
    /**
     * 尾指针
     */
    private Node tail;
    /**
     * 总数量
     */
    private int size;

    private class Node {
        Integer val;
        Node prev;
        Node next;

        public Node(Integer val) {
            this.val = val;
        }
    }

    public MyLinkedList() {
    }

    public int get(int index) {
        Node node = getIndexNode(index);
        if (node == null) {
            return -1;
        }
        return node.val;
    }

    public void addAtHead(int val) {
        if (init(val)) {
            return;
        }
        Node node = new Node(val);
        node.next = head;
        head.prev = node;
        head = node;
        size++;
    }

    public void addAtTail(int val) {
        if (init(val)) {
            return;
        }
        Node node = new Node(val);
        tail.next = node;
        node.prev = tail;
        tail = node;
        size++;
    }

    public void addAtIndex(int index, int val) {
        if (index > size) {
            return;
        }
        if (index == 0) {
            addAtHead(val);
            return;
        }
        if (index == size) {
            addAtTail(val);
            return;
        }
        if (init(val)) {
            return;
        }
        Node node = getIndexNode(index);
        if (node == null) {
            return;
        }
        Node prev = node.prev;
        Node newNode = new Node(val);
        prev.next = newNode;
        newNode.prev = prev;
        newNode.next = node;
        node.prev = newNode;
        size++;
    }

    public void deleteAtIndex(int index) {
        if (index >= size) {
            return;
        }
        Node node = getIndexNode(index);
        if (node == null) {
            return;
        }
        Node prev = node.prev;
        if (prev == null) {
            head = node.next;
        } else {
            prev.next = node.next;
            node.prev = prev;
        }
        node.next = null;
        size--;
    }


    private Node getIndexNode(int index) {
        if (head == null || index >= size) {
            return null;
        }
        if (index == 0) {
            return head;
        }
        Node current = head;
        int i = 0;
        while (i <= index && current != null) {
            if (i == index) {
                return current;
            }
            i++;
            current = current.next;
        }
        return null;
    }

    private boolean init(int val) {
        if (head == null) {
            head = new Node(val);
            tail = head;
            size++;
            return true;
        }
        return false;
    }


    public static void main(String[] args) {
        MyLinkedList linkedList = new MyLinkedList();
//        linkedList.addAtHead(7);
//        linkedList.addAtHead(2);
//        linkedList.addAtHead(1);
//        //链表变为1-> 2-> 3
//        linkedList.addAtIndex(3, 0);
//        //现在链表是1-> 3
//        linkedList.deleteAtIndex(2);
//        linkedList.addAtHead(6);
//        linkedList.addAtTail(4);
//        System.out.println(linkedList.get(4));
//        linkedList.addAtHead(4);
//        linkedList.addAtIndex(5, 0);
//        linkedList.addAtHead(6);

        linkedList.addAtIndex(0,10);
        linkedList.addAtIndex(0,20);
        linkedList.addAtIndex(1,30);
        System.out.println(linkedList.get(0));
    }
}
