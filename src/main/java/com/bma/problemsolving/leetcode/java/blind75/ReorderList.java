package com.bma.problemsolving.leetcode.java.blind75;

public class ReorderList {

    public static class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }

    public void reorderList(ListNode head) {
        // Step 1. Find the middle of the list
        ListNode slow = head;
        ListNode fast = head;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }

        // Step 2. Reverse the second half of the list
        ListNode curr = slow;
        ListNode prev = null;
        while (curr != null) {
            ListNode tmp = curr.next;
            curr.next = prev;
            prev = curr;
            curr = tmp;
        }

        // Step 3. Merge both halves
        ListNode left = head;
        ListNode right = prev;

        assert right != null;

        while (right.next != null) {
            assert left != null;
            ListNode temp1 = left.next;
            left.next = right;
            left = temp1;

            ListNode temp2 = right.next;
            right.next = left;
            right = temp2;
        }

    }
}
