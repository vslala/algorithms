package com.bma.problemsolving.leetcode.java.linkedlist;

import com.bma.fixtures.Fixtures;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

class MiddleOfLinkedListTest {

    private MiddleOfLinkedList sol = new MiddleOfLinkedList();

    @ParameterizedTest
    @CsvSource({
            "1_2_3_4_5, 3",
            "1_2_3_4_5_6, 4",
    })
    void shouldReturnTheMiddleNodeOfLinkedList(String inputStr, int expectedResult) {
        var head = ListNode.generateListFromIntegerArray(Fixtures.splitAndParseArr(inputStr, "_"));
        var result = sol.middleNode(head);

        assertEquals(expectedResult, result.getVal());
    }

}