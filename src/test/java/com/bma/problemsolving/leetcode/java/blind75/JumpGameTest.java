package com.bma.problemsolving.leetcode.java.blind75;

import com.bma.fixtures.Fixtures;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

class JumpGameTest {

    @ParameterizedTest
    @CsvSource(value = {
            "[2,3,1,1,4] true",
            "[3,2,1,0,4] false",
            "[0] true",
            "[1] true",
            "[1,1,1,1,1,1,1,1] true",
            "[0,1,1,1,1,1,1,1] false",
            "[0,0,0,0] false",
            "[5,0,0,0,0,1,0] true",
            "[8,2,4,4,4,9,5,2,5,8,8,0,8,6,9,1,1,6,3,5,1,2,6,6,0,4,8,6,0,3,2,8,7,6,5,1,7,0,3,4,8,3,5,9,0,4,0,1,0,5,9,2,0,7,0,2,1,0,8,2,5,1,2,3,9,7,4,7,0,0,1,8,5,6,7,5,1,9,9,3,5,0,7,5] true"
    }, delimiter = ' ')
    void given_an_array_of_jump_length_find_out_if_theres_a_path_from_index_0_to_end_index(String inputExpr, boolean expectedOutput) {
        int[] input = Fixtures.parse1DArray(inputExpr);
        var sol = new JumpGame();
        boolean output = sol.canJump(input);

        assertEquals(expectedOutput, output);
    }
}