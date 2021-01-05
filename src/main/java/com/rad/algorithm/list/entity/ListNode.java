package com.rad.algorithm.list.entity;

/**
 * 单链表节点的结构
 * @author: rad
 * @date: 2021/1/5
 */
public class ListNode<T> {

    public T val;
    public ListNode<T> next;
    public ListNode(T val) {
        this.val = val;
    }

}
