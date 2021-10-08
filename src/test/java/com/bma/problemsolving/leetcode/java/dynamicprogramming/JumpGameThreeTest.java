package com.bma.problemsolving.leetcode.java.dynamicprogramming;

import com.bma.fixtures.Fixtures;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

class JumpGameThreeTest {

    private JumpGameThree jumpGameThree = new JumpGameThree();

    @ParameterizedTest
    @CsvSource({
            "4_2_3_0_3_1_2, 5, true",
            "4_2_3_0_3_1_2, 0, true",
            "3_0_2_1_2, 2, false"
    })
    void checkIfYouCanReachToAnyIndexWithValueZero(String inputArrStr, int startIndex, boolean expected) {
        assertEquals(expected, jumpGameThree.canReach(Fixtures.splitAndParseArr(inputArrStr, "_"), startIndex));
    }

}