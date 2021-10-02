package com.bma.problemsolving.leetcode.java.linkedlist;

import java.util.Objects;

/**
 * You are given two non-empty linked lists representing two non-negative integers.
 * The digits are stored in reverse order, and each of their nodes contains a single digit.
 * Add the two numbers and return the sum as a linked list.
 * You may assume the two numbers do not contain any leading zero, except the number 0 itself.
 *
 * @author varun.shrivastava
 */
public class AddTwoNumbers {

    public ListNode<Integer> addTwoNumbers(ListNode<Integer> l1, ListNode<Integer> l2) {
        ListNode<Integer> output = null;
        ListNode<Integer> lastNode = null;
        var l1Itr = l1;
        var l2Itr = l2;
        var carry = 0;

        while (l1Itr != null || l2Itr != null) {
            int val1 = getValOrDefault(l1Itr);
            int val2 = getValOrDefault(l2Itr);
            int sum = val1 + val2 + carry;
            carry = sum / 10;


            if (isListEmpty(output)) {
                output = new ListNode<>(sum % 10);
                lastNode = output;
            } else {
                var listNode = new ListNode<>(sum % 10);
                lastNode.next = listNode;
                lastNode = listNode;
            }

            l1Itr = getNextOrNull(l1Itr);
            l2Itr = getNextOrNull(l2Itr);
        }

        if (carry > 0) {
            lastNode.next = new ListNode<>(carry);
        }

        return output;
    }

    private boolean isListEmpty(ListNode<Integer> listNode) {
        return listNode == null;
    }

    private ListNode<Integer> getNextOrNull(ListNode<Integer> listNode) {
        return Objects.isNull(listNode) ? null : listNode.next;
    }

    private int getValOrDefault(ListNode<Integer> listNode) {
        return Objects.isNull(listNode) ? 0 : listNode.val;
    }

}
