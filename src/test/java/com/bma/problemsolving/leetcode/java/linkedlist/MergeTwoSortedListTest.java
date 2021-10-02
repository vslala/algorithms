package com.bma.problemsolving.leetcode.java.linkedlist;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MergeTwoSortedListTest {

    @ParameterizedTest
    @CsvSource({
            "1_2_4, 1_3_4, 1_1_2_3_4_4"
    })
    void shouldMergeTwoSortedLists(String l1ArrStr, String l2ArrStr, String outputStr) {
        ListNode<Integer> l1 = ListNode.generateListFromIntegerArray(Arrays.stream(l1ArrStr.split("_")).mapToInt(Integer::parseInt).toArray());
        ListNode<Integer> l2 = ListNode.generateListFromIntegerArray(Arrays.stream(l2ArrStr.split("_")).mapToInt(Integer::parseInt).toArray());

        var sol = new MergeTwoSortedList();

        var result = sol.mergeTwoLists(l1, l2);
        var output = Arrays.stream(outputStr.split("_")).mapToInt(Integer::parseInt).toArray();
        for (int val : output) {
            assertEquals(val, result.val);
            result = result.next;
        }
    }

}