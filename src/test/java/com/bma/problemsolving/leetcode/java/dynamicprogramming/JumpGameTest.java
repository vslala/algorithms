package com.bma.problemsolving.leetcode.java.dynamicprogramming;

import com.bma.fixtures.Fixtures;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

class JumpGameTest {

    private JumpGame jumpGame = new JumpGame();

    @ParameterizedTest
    @CsvSource({
            "2_3_1_1_4, true",
            "3_2_1_0_4, false",
            "0, true",
            "1, true",
            "1_1_1_1_1_1_1_1, true",
            "0_1_1_1_1_1_1_1, false",
            "0_0_0_0, false",
            "5_0_0_0_0_1_0, true",
            "8_2_4_4_4_9_5_2_5_8_8_0_8_6_9_1_1_6_3_5_1_2_6_6_0_4_8_6_0_3_2_8_7_6_5_1_7_0_3_4_8_3_5_9_0_4_0_1_0_5_9_2_0_7_0_2_1_0_8_2_5_1_2_3_9_7_4_7_0_0_1_8_5_6_7_5_1_9_9_3_5_0_7_5, true"
    })
    void shouldFindIfCanJumpFromStartIndexToEnd(String input, boolean expectedOutput) {
        assertEquals(expectedOutput, jumpGame.canJump(Fixtures.splitAndParseArr(input, "_")));
    }
}
