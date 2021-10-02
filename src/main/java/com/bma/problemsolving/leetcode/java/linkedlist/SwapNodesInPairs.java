package com.bma.problemsolving.leetcode.java.linkedlist;

/**
 * 24. Swap Nodes in Pairs
 * Given a linked list, swap every two adjacent nodes and return its head.
 * You must solve the problem without modifying the values in the list's nodes (i.e., only nodes themselves may be changed.)
 *
 * @author varun.shrivastava
 */
public class SwapNodesInPairs {

    // 1 -> 2 -> 3 -> 4
    // 2 -> 1
    // 4 -> 3
    // 2 -> 1 -> 4 -> 3
    public ListNode swapPairs(ListNode head) {
        if (head == null || head.next == null) return head;

        var result = head.next;

        var first = head;
        var second = first.next;
        var third = second.next;

        while (true) {
            second.next = first; // 2 -> 1

            if (third == null || third.next == null) {
                first.next = third;
                return result;
            } else {
                first.next = third.next;    // 1 -> 4
            }

            first = third;          // 1 = 3
            second = first.next;    // 2 = 4
            third = second.next;    // 3 = 5
        }


    }
}
