package com.bma.problemsolving.leetcode.java.array;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MaximumSwapTest {

    private MaximumSwap sol = new MaximumSwap();

    @ParameterizedTest
    @CsvSource({
            "273, 723",
            "2736, 7236",
            "9973, 9973",
            "1100, 1100",
            "1234, 4231",
            "19, 91",
            "107, 701"
    })
    void shouldReturnTheMaximumNumberWithJustOneSwap(int input, int expected) {
        int output = sol.maximumSwap(input);

        assertEquals(expected, output);
    }
}