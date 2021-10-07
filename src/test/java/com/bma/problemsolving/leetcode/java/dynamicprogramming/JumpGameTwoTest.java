package com.bma.problemsolving.leetcode.java.dynamicprogramming;

import com.bma.fixtures.Fixtures;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

class JumpGameTwoTest {

    private JumpGameTwo jumpGame = new JumpGameTwo();

    @ParameterizedTest
    @CsvSource({
            "0, 0",
            "1, 0",
            "2_3_1_1_4, 2",
            "2_3_0_1_4, 2",
            "1_1_1_1_1_1_1_1, 7",
            "5_0_0_0_0_1_0, 2",
            "8_2_4_4_4_9_5_2_5_8_8_0_8_6_9_1_1_6_3_5_1_2_6_6_0_4_8_6_0_3_2_8_7_6_5_1_7_0_3_4_8_3_5_9_0_4_0_1_0_5_9_2_0_7_0_2_1_0_8_2_5_1_2_3_9_7_4_7_0_0_1_8_5_6_7_5_1_9_9_3_5_0_7_5, 13"
    })
    void shouldGiveMinimumNumberOfJumpsRequiredToReachTheLastIndex(String input, int expectedOutput) {
        var result = jumpGame.jump(Fixtures.splitAndParseArr(input, "_"));
        assertEquals(expectedOutput, result);
    }
}