package com.rad.algorithm.list;

import com.rad.algorithm.list.entity.ListNode;
import com.rad.algorithm.list.entity.SingleLinkedList;

/**
 * 反转链表
 * https://leetcode-cn.com/problems/reverse-linked-list-ii/
 * @author: rad
 * @date: 2021/1/5
 */
public class ReverseListNode {

    public static void main(String[] args) {
        SingleLinkedList sll = new SingleLinkedList(6);
        System.out.println(SingleLinkedList.printSingleLinkedList(sll.head));
//        ListNode<Integer> reverseNode = reverse(sll.head);
//        System.out.println(SingleLinkedList.printSingleLinkedList(reverseNode));

//        ListNode<Integer> reverseNode = reverseN(sll.head, 3);
//        System.out.println(SingleLinkedList.printSingleLinkedList(reverseNode));

        ListNode<Integer> reverseNode = reverseBetween(sll.head, 3, 4);
        System.out.println(SingleLinkedList.printSingleLinkedList(reverseNode));
    }

    public static ListNode<Integer> reverse(ListNode<Integer> head) {
        if (head.next == null) {
            return head;
        }
        ListNode<Integer> last = reverse(head.next);
        head.next.next = head;
        head.next = null;
        return last;
    }

    // 后驱节点
    private static ListNode<Integer> successor = null;

    /**
     * 反转以 head 为起点的 n 个节点，返回新的头结点
     * @param head 起点
     * @param n 第n个节点
     * @return 反转后的节点
     */
    public static ListNode<Integer> reverseN(ListNode<Integer> head, int n) {
        // base case
        if (n == 1) {
            // 记录第 n + 1 个节点
            successor = head.next;
            return head;
        }
        // 以 head.next 为起点，需要反转前 n - 1 个节点
        ListNode<Integer> last = reverseN(head.next, n - 1);
        head.next.next = head;
        // 让反转之后的 head 节点和后面的节点连起来
        head.next = successor;
        return last;
    }

    /**
     * 反转链表的一部分
     * @param head 起点
     * @param m 第m个节点
     * @param n 第n个节点
     * @return 反转后的节点
     */
    public static ListNode<Integer> reverseBetween(ListNode<Integer> head, int m, int n) {
        // base case: 如果 m == 1，就相当于反转链表开头的 n 个元素嘛
        if (m == 1) {
            // 相当于反转前 n 个元素
            return reverseN(head, n);
        }
        // 前进到反转的起点触发 base case:
        // 如果把 head.next 的索引视为 1 呢？那么相对于 head.next，反转的区间应该是从第 m - 1 个元素开始的；
        head.next = reverseBetween(head.next, m - 1, n -1);
        return head;
    }


}
