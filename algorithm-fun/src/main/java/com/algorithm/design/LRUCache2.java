package com.algorithm.design;

import java.util.HashMap;
import java.util.Map;

/**
 * @author wenjing.zsm
 * @version 1.0
 * @title:
 * @description:
 * @since 2024/5/21 11:31 下午
 */
public class LRUCache2<E, U> {

    static class Node<E, U> {
        E key;
        U value;
        Node<E, U> next, prev;

        public Node(E key, U value) {
            this.key = key;
            this.value = value;
        }

        protected Node() {}
    }
    final int capacity;
    Map<E, Node<E, U>> cache = new HashMap<>();
    Node<E, U> dummy = new Node<E, U>();

    public LRUCache2(int capacity) {
        this.capacity = capacity;
        dummy.prev = dummy;
        dummy.next = dummy;
    }

    public U get(E key) {
        Node<E, U> node = getNode(key);
        return node != null ? node.value : null;
    }

    public void put(E key, U value) {
        Node<E, U> node = getNode(key);
        if(node != null) {
            node.value = value;
            return;
        }
        node = new Node<E, U>(key, value);
        cache.put(key, node);
        putFront(node);
        if(cache.size() > capacity) {
            Node<E, U> last = dummy.prev;
            remove(last);
            cache.remove(last.key);
        }
    }

    private Node<E, U> getNode(E key) {
        if(!cache.containsKey(key)) {
            return null;
        }
        Node<E, U> node = cache.get(key);
        remove(node);
        putFront(node);
        return node;
    }

    void remove(Node<E, U> x) {
        x.prev.next = x.next;
        x.next.prev = x.prev;
    }

    void putFront(Node<E, U> x) {
        x.prev = dummy;
        x.next = dummy.next;
        x.prev.next = x;
        x.next.prev = x;
    }
}
