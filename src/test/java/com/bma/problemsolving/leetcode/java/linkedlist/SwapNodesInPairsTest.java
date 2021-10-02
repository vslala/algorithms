package com.bma.problemsolving.leetcode.java.linkedlist;

import com.bma.fixtures.Fixtures;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SwapNodesInPairsTest {

    @Test
    void shouldCheckForCornerCases() {
        var listNode = new ListNode<Integer>();

        var sol = new SwapNodesInPairs();
        var output = sol.swapPairs(listNode);
        assertEquals(listNode, output);

        listNode = ListNode.generateListFromIntegerArray(new int[]{1});
        output = sol.swapPairs(listNode);
        assertEquals(listNode, output);
    }

    @ParameterizedTest
    @CsvSource({
            "1_2_3_4, 2_1_4_3",
            "1_2_3_4_5, 2_1_4_3_5",

    })
    void shouldSwapTheAdjacentNodesWithoutChangingTheValuesInside(String inputArr, String expectedArr) {
        var head = ListNode.generateListFromIntegerArray(Fixtures.splitAndParseArr(inputArr, "_"));

        var sol = new SwapNodesInPairs();
        var result = sol.swapPairs(head);

        var expectedOutput = Fixtures.splitAndParseArr(expectedArr, "_");
        for (int val : expectedOutput) {
            assertEquals(val, result.val);
            result = result.next;
        }

    }
}