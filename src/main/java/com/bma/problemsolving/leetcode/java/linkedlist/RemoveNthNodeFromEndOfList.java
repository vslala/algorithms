package com.bma.problemsolving.leetcode.java.linkedlist;

import lombok.extern.slf4j.Slf4j;

/**
 * 19. Remove Nth Node From End of List
 * Given the head of a linked list, remove the nth node from the end of the list and return its head.
 *
 * @author varun.shrivastava
 */
@Slf4j
public class RemoveNthNodeFromEndOfList {

    public ListNode<Integer> removeNthFromTheEnd(ListNode<Integer> head, int n) {
        var pt1 = head;
        var pt2 = head;

        // move pt1 to nth node
        for (; pt1 != null; n--) {
            pt1 = pt1.next;
            if (n < 0)
                pt2 = pt2.next;
        }

        if (n == 0) return pt2.next; // null or next

        assert pt2 != null;
        pt2.next = pt2.next.next;
        return head;
    }
}
