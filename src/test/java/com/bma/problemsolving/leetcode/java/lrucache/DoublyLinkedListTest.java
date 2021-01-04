package com.bma.problemsolving.leetcode.java.lrucache;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.LinkedHashMap;

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


    @Test
    void sandbox() {
//        var hm = new HashMap<Integer, String>();
//        hm.put(1, "Varun Shrivastava");
//        hm.put(2, "Vaibhav Shrivastava");
//        hm.put(3, "Kavita Shrivastava");
//        hm.put(4, "Rajesh Shrivastava");
//        hm.forEach((key, val) -> System.out.println(key + ":" + val));
//        System.out.println();
//        System.out.println();


        var lhm = new LinkedHashMap<Integer, String>(2, 2, true);
        lhm.put(1, "Varun Shrivastava");
        lhm.put(2, "Vaibhav Shrivastava");
        lhm.put(3, "Kavita Shrivastava");
        lhm.put(4, "Rajesh Shrivastava");

        var first = lhm.entrySet().iterator().next();
        lhm.get(1);

        // get O(1)
        // put O(1)

        lhm.forEach((key,value) -> System.out.println(key + ":" + value));
    }
}