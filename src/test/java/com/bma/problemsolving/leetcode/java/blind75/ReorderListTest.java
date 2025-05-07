package com.bma.problemsolving.leetcode.java.blind75;

import com.bma.algorithms.sort.elementary.Util;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ReorderListTest {

    @Test
    void it_should_reorder_the_list_in_order_l0_ln_l1_ln_minus_1_and_so_on() {
        ReorderList.ListNode head = new ReorderList.ListNode(1,
                new ReorderList.ListNode(2,
                        new ReorderList.ListNode(3, new ReorderList.ListNode(4))));

        var sol = new ReorderList();
        sol.reorderList(head);

        assertEquals(1, head.val);
        assertEquals(4, head.next.val);
        assertEquals(2, head.next.next.val);
        assertEquals(3, head.next.next.next.val);
        assertNull(head.next.next.next.next);
    }

}