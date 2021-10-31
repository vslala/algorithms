package com.bma.problemsolving.leetcode.java.array;

import com.bma.fixtures.Fixtures;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class TwoSum2Test {

    private TwoSum2 sol = new TwoSum2();

    @ParameterizedTest
    @CsvSource({
            "2_7_11_15, 9, 1_2",
            "2_3_4, 6, 1_3",
            "-1_0, -1, 1_2",
    })
    void shouldReturnIndicesWhoseValueSumsUpToAGivenTargetValue(String inputArrStr, int target, String expectedArrStr) {
        var original = Fixtures.splitAndParseArr(inputArrStr, "_");
        var numbers = Fixtures.splitAndParseArr(inputArrStr, "_");
        var expected = Fixtures.splitAndParseArr(expectedArrStr, "_");

        var result = sol.twoSum(numbers, target);

        Fixtures.assertArrayEquals(original, expected, result);
    }

}