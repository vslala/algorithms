package com.bma.problemsolving.leetcode.java.linkedlist;

import com.bma.fixtures.Fixtures;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RotateListTest {

    private RotateList sol = new RotateList();

    @ParameterizedTest
    @CsvSource({
            "1_2_3_4_5, 2, 4_5_1_2_3",
            "1_2_3_4_5, 4, 2_3_4_5_1",
            "1_2_3_4_5, 3, 3_4_5_1_2",
            "1_2_3, 3, 1_2_3",
            "1_2, 3, 2_1",
            "1_2, 2, 1_2",
            "1_2, 1, 2_1",
            "1, 5, 1",
            "1_2, 0, 1_2",
            "1_2_3, 2000000000, 2_3_1"
    })
    void shouldRotateListByGivenNumber(String input, int times, String output) {
        var head = ListNode.generateListFromIntegerArray(Fixtures.splitAndParseArr(input, "_"));
        var expected = Fixtures.splitAndParseArr(output, "_");

        var result = sol.rotateRight(head, times);

        for (int val : expected) {
            assertEquals(val, result.val);
            result = result.next;
        }
    }

}