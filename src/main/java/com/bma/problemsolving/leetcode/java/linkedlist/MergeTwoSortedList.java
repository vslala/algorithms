package com.bma.problemsolving.leetcode.java.linkedlist;

/**
 * Merge two sorted linked lists and return it as a sorted list. The list should be made by splicing together the nodes of the first two lists.
 * https://leetcode.com/problems/merge-two-sorted-lists/
 *
 * @author varun.shrivastava
 */
public class MergeTwoSortedList {

    public ListNode<Integer> mergeTwoLists(ListNode<Integer> l1, ListNode<Integer> l2) {
        var head = new ListNode<Integer>();
        var itr = head;

        while (l1 != null || l2 != null) {
            if (l1 == null) {
                itr.next = l2;
                itr = itr.next;
                l2 = l2.next;
            } else if (l2 == null) {
                itr.next = l1;
                itr = itr.next;
                l1 = l1.next;
            } else if (l1.val <= l2.val) {
                itr.next = l1;
                itr = itr.next;
                l1 = l1.next;
            } else {
                itr.next = l2;
                itr = itr.next;
                l2 = l2.next;
            }

        }

        return head.next;
    }
}
