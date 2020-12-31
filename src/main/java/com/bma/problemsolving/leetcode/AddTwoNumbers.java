package com.bma.problemsolving.leetcode;

import lombok.Getter;
import lombok.Setter;

import java.util.Objects;

public class AddTwoNumbers {

    @Setter
    @Getter
     public static class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode output = null;
        ListNode lastNode = null;
        var l1Itr = l1;
        var l2Itr = l2;
        int carry = 0;
        boolean runOneMoreTime = false;

        while (l1Itr != null || l2Itr != null) {
            int val1 = getValOrDefault(l1Itr);
            int val2 = getValOrDefault(l2Itr);
            int sum = val1 + val2 + carry;
            carry = sum / 10;


            if (isListEmpty(output)) {
                output = new ListNode(sum % 10);
                lastNode = output;
            } else {
                var listNode = new ListNode(sum % 10);
                lastNode.next = listNode;
                lastNode = listNode;
            }

            l1Itr = getNextOrNull(l1Itr);
            l2Itr = getNextOrNull(l2Itr);
        }

        if (carry > 0) {
            lastNode.next = new ListNode(carry);
        }

        return output;
    }

    private boolean isListEmpty(ListNode listNode) {
        return listNode == null;
    }

    private ListNode getNextOrNull(ListNode listNode) {
        return Objects.isNull(listNode) ? null : listNode.next;
    }

    private int getValOrDefault(ListNode listNode) {
        return Objects.isNull(listNode) ? 0 : listNode.val;
    }

}
