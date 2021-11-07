package com.bma.problemsolving.leetcode.java.dfsbfs;

import com.bma.fixtures.Fixtures;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RottingOrangesTest {

    private RottingOranges sol = new RottingOranges();

    @ParameterizedTest
    @CsvSource({
            "[[2_1_1]:[1_1_0]:[0_1_1]], 4",
            "[[2_1_1]:[0_1_1]:[1_0_1]], -1",
            "[[0_2]], 0",
            "[[1_1_1]:[1_1_1]:[0_0_2]], 4",
            "[[1]], -1",
    })
    void shouldReturnMinimumNumberOfMinutesThatMustElapseUntilNoCellHasAFreshOrange(String inputMatrixStr, int expected) {
        var original = Fixtures.convertToPrimitiveArrMatrix(Fixtures.parseExpression(inputMatrixStr));
        var matrix = Arrays.copyOf(original, original.length);

        var result = sol.orangesRotting(matrix);

        assertEquals(expected, result);
    }

}