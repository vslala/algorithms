package com.bma.problemsolving.leetcode.java.linkedlist;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RemoveNthNodeFromEndOfListTest {

    @ParameterizedTest
    @CsvSource({
            "1_2_3_4_5, 2, 1_2_3_5",
            "1_2, 1, 1",
            "1, 1, null",
            "1_2, 2, 2"
    })
    void shouldRemoveTheNthNodeFromTheEnd(String testArrStr, int nth, String outputArrStr) {
        // given
        var testArr = Arrays.stream(testArrStr.split("_")).mapToInt(Integer::parseInt).toArray();
        var outputArr = outputArrStr.equals("null") ? new int[0] : Arrays.stream(outputArrStr.split("_")).mapToInt(Integer::parseInt).toArray();
        var listHead = ListNode.generateListFromIntegerArray(testArr);
        var sol = new RemoveNthNodeFromEndOfList();

        // when
        var output = sol.removeNthFromTheEnd(listHead, nth);

        // then
        for (var i = 0; i < outputArr.length; i++, output = output.next) {
            assertEquals(outputArr[i], output.val);
        }
    }

}