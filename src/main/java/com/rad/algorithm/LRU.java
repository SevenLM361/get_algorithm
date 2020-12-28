package com.rad.algorithm;

import java.util.HashMap;
import java.util.LinkedHashMap;

/**
 * LRU算法
 * @author rad
 * @date 2020/12/28
 */
public class LRU {

    /**
     * 链表节点
     */
    static class Node {
        public int key, val;
        public Node next, prev;

        public Node(int k, int v) {
            this.key = k;
            this.val = v;
        }
    }

    /**
     * 基于链表节点构建的双链表
     */
    static class DoubleList {
        // 头尾虚节点
        private Node head, tail;
        // 链表元素数
        private int size;

        public DoubleList() {
            // 初始化双向链表的数据
            head = new Node(0, 0);
            tail = new Node(0, 0);
            head.next = tail;
            tail.next = head;
            size = 0;
        }

        // 在链表尾部添加节点x, 时间O(1)
        // heal <-> tail
        // =>
        // head <-> (x) <-> tail
        public void addLast(Node x) {
            x.prev = tail.prev;
            x.next = tail;
            tail.prev.next = x;
            tail.prev = x;
            size++;
        }

        // 在链表尾部删除节点x（x一定存在）
        // 由于是双链表且给的是目标Node节点, 时间O(1)
        // head <-> (x) <-> tail
        // =>
        // head <-> tail
        // PS: x.next = tail
        public void remove(Node x) {
            x.prev.next = x.next;
            x.next.prev = x.prev;
            size--;
        }

        // 删除链表中第一个节点，并返回该节点，时间O(1)
        public Node removeFirst() {
            if (head.next == tail) {
                return null;
            }
            Node first = head.next;
            remove(first);
            return first;
        }

        public int size() {
            return size;
        }
    }

    static class LRUCache {
        private HashMap<Integer, Node> map;

        private DoubleList cache;

        private int cap;

        public LRUCache(int capacity) {
            this.cap = capacity;
            map = new HashMap<>();
            cache = new DoubleList();
        }

        /**
         * 将某个 key 提升为最近使用的
         */
        private void makeRecently(int key) {
            Node x = map.get(key);
            // 先从链表中删除这个节点
            cache.remove(x);
            // 重新插到队尾
            cache.addLast(x);
        }

        /**
         * 添加最近使用的元素
         */
        private void addRecently(int key, int val) {
            Node x = new Node(key, val);
            // 将新节点插到队尾
            cache.addLast(x);
            // 别忘了在 map 中添加 key 的映射
            map.put(key, x);
        }

        /**
         * 删除某一个key
         */
        private void deleteKey(int key) {
            Node x = map.get(key);
            // 从链表中删除这个节点
            cache.remove(x);
            // 从map中删除
            map.remove(key);
        }

        /**
         * 删除最久未使用的元素
         */
        private void removeLeastRecently() {
            // 链表头部的第一个元素就是最久未使用的
            Node deletedNode = cache.removeFirst();
            // 从map中删除对于的key
            int deletedKey = deletedNode.key;
            map.remove(deletedKey);
        }

        /**
         * LRU get方法
         */
        public int get(int key) {
            if (!map.containsKey(key)) {
                return -1;
            }
            // 将该数据提升为最近使用的
            makeRecently(key);
            return map.get(key).val;
        }

        /**
         * LRU put方法
         */
        public void put(int key, int val) {
            if (map.containsKey(key)) {
                // 删除旧的数据
                deleteKey(key);
                // 新插入的数据为最近使用的数据
                addRecently(key, val);
                return;
            }
            // 容量已满，清理最久未使用的key
            if (cache.size() == cap) {
                removeLeastRecently();
            }
            // 添加为最近使用的元素
            addRecently(key, val);
        }

    }

    static class LRUCacheInternal {
        int cap;
        LinkedHashMap<Integer, Integer> cache = new LinkedHashMap<>();

        public LRUCacheInternal(int cap) {
            this.cap = cap;
        }

        public int get(int key) {
            if (!cache.containsKey(key)) {
                return -1;
            }
            // 将key变为最近使用
            makeRecently(key);
            return cache.get(key);
        }

        public void put(int key, int val) {
            if (cache.containsKey(key)) {
                // 新插入的数据为最近使用的数据
                cache.put(key, val);
                makeRecently(key);
                return;
            }
            // 容量已满，清理最久未使用的key
            if (cache.size() >= this.cap) {
                int oldestKey = cache.keySet().iterator().next();
                cache.remove(oldestKey);
            }
            // 添加为最近使用的元素
            cache.put(key, val);
        }

        private void makeRecently(int key) {
            int val = cache.get(key);
            // 删除 key，重新插入到队尾
            cache.remove(key);
            cache.put(key, val);
        }
    }
}
