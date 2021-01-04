package com.bma.problemsolving.leetcode.java.lrucache;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DoublyLinkedListTest {

    @Test
    void  itShouldAddNodeToTheFrontOfTheList() {
        var list = new DoublyLinkedList<Integer, Integer>();
        var node1 = new DoublyNode<>(1, 1);
        var node2 = new DoublyNode<>(2, 2);
        var node3 = new DoublyNode<>(3, 3);
        var node4 = new DoublyNode<>(4, 4);
        var node5 = new DoublyNode<>(5, 5);

        list.appendToFront(node1);
        list.appendToFront(node2);
        list.appendToFront(node3);
        list.appendToFront(node4);
        list.appendToFront(node5);

        list.print();   // 5 -> 4 -> 3 -> 2 -> 1 ->

        assertEquals(5, list.front().getData());

        list.moveToFront(node1);
        list.print();   // 1 -> 5 -> 4 -> 3 -> 2 ->
        assertEquals(1, list.front().getData());

        list.moveToFront(node3);
        list.print();   // 3 -> 1 -> 5 -> 4 -> 2 ->
        assertEquals(3, list.front().getData());

        list.removeFromTail();
        list.print();   // 3 -> 1 -> 5 -> 4 ->
        assertEquals(4, list.end().getData());

        list.moveToFront(node4);
        list.print();   // 4 -> 3 -> 1 -> 5 ->
        assertEquals(5, list.end().getData());
    }

}