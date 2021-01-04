package com.bma.problemsolving.leetcode.java.lrucache;

import com.bma.algorithms.sort.elementary.Util;

import java.util.Objects;

public class DoublyLinkedList<K, V> {

    private DoublyNode<K, V> head;
    private DoublyNode<K, V> tail;

    public void appendToFront(DoublyNode<K, V> node) {
        if (Objects.isNull(head)) {
            head = node;
            tail = head;
        }
        else {
            node.setNext(head);
            node.setPrev(null);
            head.setPrev(node);
            head = node;
        }
    }

    public void removeFromTail() {
        var temp = tail.getPrev();
        temp.setNext(null);
        tail = temp;
    }

    public void moveToFront(DoublyNode<K, V> node) {
        if (node.equals(tail)) {
            tail = tail.getPrev();
            tail.setNext(null);
            appendToFront(node);
            return;
        }

        node.getPrev().setNext(node.getNext());
        node.getNext().setPrev(node.getPrev());
        appendToFront(node);
    }

    public DoublyNode<K, V> front() {
        return head;
    }

    public DoublyNode<K, V> end() {
        return tail;
    }

    public void print() {
        var itr = head;
        while (itr != null) {
            Util.print(itr.getData() + " -> ");
            itr = itr.getNext();
        }
        Util.println();
    }
}
