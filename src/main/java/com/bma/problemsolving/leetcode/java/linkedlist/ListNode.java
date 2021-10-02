package com.bma.problemsolving.leetcode.java.linkedlist;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ListNode<T> {
    T val;
    ListNode<T> next;

    ListNode(T val) {
        this.val = val;
    }

    public static ListNode<Integer> generateListFromIntegerArray(int[] items) {
        var tail = new ListNode<>(items[0]);
        var head = tail;
        for (var i = 1; i < items.length; i++) {
            tail.next = new ListNode<>(items[i]);
            tail = tail.next;
        }

        return head;
    }
}
