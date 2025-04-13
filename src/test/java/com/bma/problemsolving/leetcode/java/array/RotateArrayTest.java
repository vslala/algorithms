package com.bma.problemsolving.leetcode.java.array;

import com.bma.fixtures.Fixtures;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class RotateArrayTest {

    private RotateArray sol = new RotateArray();

    @ParameterizedTest
    @CsvSource({
            "1_2_3_4_5_6_7, 3, 5_6_7_1_2_3_4",
            "1_2_3_4_5_6_7, 1, 7_1_2_3_4_5_6",
            "-1_-100_3_99, 2, 3_99_-1_-100"
    })
    void shouldRotateTheArrayToTheRightByGivenSteps(String inputArrStr, int steps, String expectedArrStr) {
        var original = Fixtures.splitAndParseArr(inputArrStr, "_");
        var input = Fixtures.splitAndParseArr(inputArrStr, "_");
        var expected = Fixtures.splitAndParseArr(expectedArrStr, "_");

        sol.rotate(input, steps);

        Fixtures.assertArrayEquals(original, expected, input);
    }

}