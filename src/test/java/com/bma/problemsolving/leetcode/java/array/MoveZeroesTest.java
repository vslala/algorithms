package com.bma.problemsolving.leetcode.java.array;

import com.bma.fixtures.Fixtures;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

class MoveZeroesTest {

    private MoveZeroes sol = new MoveZeroes();

    @ParameterizedTest
    @CsvSource({
            "0_1_0_3_12, 1_3_12_0_0",
            "1, 1",
            "0, 0"
    })
    void shouldMoveZeroesToTheEndWhileMaintainingTheRelativeOrderOrTheNonZeroElement(String inputStr, String expectedStr) {
        var original = Fixtures.splitAndParseArr(inputStr, "_");
        var nums = Fixtures.splitAndParseArr(inputStr, "_");
        var expected = Fixtures.splitAndParseArr(expectedStr, "_");

        sol.moveZeroes(nums);

        Fixtures.assertArrayEquals(original, nums, expected);
    }

}