package com.bma.problemsolving.meta.design;

import lombok.Getter;
import lombok.experimental.Accessors;

import java.util.*;

/**
 * Implement Ordered Set to support following functions:
 * - add(num) with O(1)
 * - remove(num) with O(1)
 * - toArray() with O(n)
 * - contains(num) with O(1)
 *
 * @author varun.shrivastava
 */
class OrderedSet<T> {

    private Node<T> first;
    private Node<T> last;
    private Map<T, Node<T>> map;

    @Getter
    @Accessors(fluent = true)
    private int size = 0;

    public OrderedSet() {
        first = new Node<>();
        last = new Node<>();
        first.next = last;
        last.prev = first;

        map = new HashMap<>();
    }

    public void add(T item) {
        if (map.containsKey(item)) {
            var node = map.get(item);
            moveNodeToLast(node);
        } else {
            Node<T> newNode = new Node<>(item);
            addNodeToLast(newNode);
            map.put(item, newNode);
            size++;
        }
    }

    private void moveNodeToLast(Node<T> node) {
        var prev = node.prev;
        var next = node.next;

        prev.next = next;
        next.prev = prev;

        addNodeToLast(node);
    }

    private void addNodeToLast(Node<T> node) {
        node.next = last;
        node.prev = last.prev;
        last.prev.next = node;
        last.prev = node;
    }

    public boolean contains(T item) {
        return map.containsKey(item);
    }

    public List<T> toArray() {
        List<T> items = new ArrayList<>();
        for (Node<T> n = first.next; n != last; n = n.next) {
            items.add(n.val);
        }
        return items;
    }

    public void remove(T item) {
        if (map.containsKey(item)) {
            var node = map.get(item);
            removeNodeFromList(node);
            map.remove(item);
            size--;
        } else {
            throw new MissingResourceException("Provided item not found", OrderedSet.class.getName(), String.valueOf(item));
        }
    }

    private void removeNodeFromList(Node<T> node) {
        var temp = node.prev;
        node.prev = node.next;
        node.next.prev = temp;
    }

    private static class Node<E> {
        Node<E> next;
        Node<E> prev;
        E val;

        public Node() {}
        public Node(E item) {
            this.val = item;
        }
    }
}
