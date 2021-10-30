package com.bma.problemsolving.leetcode.java.array;

import com.bma.fixtures.Fixtures;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class SquareOfASortedArrayTest {

    private SquareOfASortedArray sol = new SquareOfASortedArray();

    @ParameterizedTest
    @CsvSource({
            "-4_-1_0_3_10, 0_1_9_16_100",
            "-7_-3_2_3_11, 4_9_9_49_121"
    })
    void shouldSquareEveryElementOfTheArrayAndReturnSortedAscendingArray(String sortedInput, String expected) {
        var nums = Fixtures.splitAndParseArr(sortedInput, "_");
        var expectedResult = Fixtures.splitAndParseArr(expected, "_");

        Fixtures.assertArrayEquals(expectedResult, sol.sortedSquares(nums));
    }

}