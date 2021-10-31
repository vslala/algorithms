package com.bma.problemsolving.leetcode.java.linkedlist;

/**
 * 876. Middle of the Linked List
 * Given the head of a singly linked list, return the middle node of the linked list.
 *
 * If there are two middle nodes, return the second middle node.
 *
 * @author varun.shrivastava
 */
public class MiddleOfLinkedList {

    /**
     * Fast and Slow Pointer
     * When traversing the list with a pointer slow, make another pointer fast that traverses twice as fast.
     * When fast reaches the end of the list, slow must be in the middle.
     *
     * Complexity Analysis
     * Time Complexity: O(N)O(N), where NN is the number of nodes in the given list.
     * Space Complexity: O(1)O(1), the space used by slow and fast.
     *
     * @param head head node of the list
     * @return required list node
     */
    public ListNode<Integer> middleNode(ListNode<Integer> head) {
        var slow = head;
        var fast = head;

        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        return slow;
    }
}
