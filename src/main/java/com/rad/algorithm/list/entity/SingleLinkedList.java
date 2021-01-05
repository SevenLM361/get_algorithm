package com.rad.algorithm.list.entity;

import java.util.ArrayList;

/**
 * 单链表
 * @author: rad
 * @date: 2021/1/5
 */
public class SingleLinkedList {

    public ListNode<Integer> head;

    public SingleLinkedList() {
        this.head = new ListNode<>(1);
    }

    public SingleLinkedList(int n) {
        this();
        ListNode<Integer> curr = this.head;
        for (int i = 2; i <= n; i++) {
            curr.next = new ListNode<>(i);
            curr = curr.next;
        }
    }

    public static ArrayList<Integer> printSingleLinkedList(ListNode node) {
        ArrayList<Integer> arrayList = new ArrayList<Integer>();
        if(node == null) {
            return arrayList;
        }
        while(node != null) {
            // 始终将遍历到链表的数据插入到arrayList的第一个位置，即保证输出结果与原链表逆序
            arrayList.add((Integer) node.val);
            node = node.next;
        }
        return arrayList;
    }

}
