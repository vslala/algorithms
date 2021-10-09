package com.bma.problemsolving.leetcode.java.dynamicprogramming;

import com.bma.fixtures.Fixtures;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

class JumpGameFourTest {

    private JumpGameFour jumpGameFour = new JumpGameFour();

    @ParameterizedTest
    @CsvSource({
            "100_-23_-23_404_100_23_23_23_3_404, 3",
            "7, 0",
            "7_6_9_6_9_6_9_7, 1",
            "11_22_7_7_7_7_7_7_7_22_13, 3",
            "2_0_2_0, 2"
    })
    void shouldCalcTheMinimumJumpsRequiredToReachTheEndIndexBasedOnConstraints(String input, int expectedOutput) {
        assertEquals(expectedOutput, jumpGameFour.minJumps(Fixtures.splitAndParseArr(input, "_")));
    }

}