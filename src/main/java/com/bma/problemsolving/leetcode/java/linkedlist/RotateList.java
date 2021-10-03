package com.bma.problemsolving.leetcode.java.linkedlist;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

/**
 * 61. Rotate List
 * Given the head of a linked list, rotate the list to the right by k places.
 *
 * @author varun.shrivastava
 */
@Slf4j
public class RotateList {
    public ListNode rotateRight(ListNode head, int k) {
        if (head == null || head.next == null || k == 0)
            return head;

        var totalNodes = len(head);
        if (totalNodes == k % totalNodes) return head;

        if (k != 1) {
            k = k % totalNodes;
            if (k == 0) {
                return head;
            }
        }


        var first = head;
        var itr = head;

        do {
            itr = first;

            var secondLast = secondLast(itr);
            var last = secondLast.next;

            last.next = first;
            secondLast.next = null;
            first = last;

            log.info("first: {}", first.val);
            log.info("secondLast: {}", secondLast.val);
            log.info("last: {}", last.val);

            k--;
        } while (k > 0);

        return first;
    }

    private List<ListNode> convertToArray(ListNode head) {
        var output = new ArrayList<ListNode>();
        var itr = head;
        do {
            output.add(itr);
            itr = itr.next;
        } while (itr != null);

        return output;
    }

    private int len(ListNode head) {
        var counter = 0;
        var itr = head;
        do {
            counter++;
            itr = itr.next;
        } while (itr != null);

        return counter;
    }

    private ListNode secondLast(ListNode secondLast) {
        while (secondLast.next.next != null) {
            secondLast = secondLast.next;
        }

        return secondLast;
    }
}
