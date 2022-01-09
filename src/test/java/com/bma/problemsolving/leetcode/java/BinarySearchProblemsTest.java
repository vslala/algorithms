package com.bma.problemsolving.leetcode.java;

import com.bma.fixtures.Fixtures;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BinarySearchProblemsTest {

    private BinarySearchProblems sol = new BinarySearchProblems();

    @ParameterizedTest
    @CsvSource({
            "1_2_3_4_5_6_7_8_9_10_11_12_13_14, 10, 9",
            "1_2_3_4_5_6_7_8_9_10_11_12_13_14, 14, 13",
            "1_2_3_4_5_6_7_8_9_10_11_12_13_14, 1, 0",
            "1_2_3_4_5_6_7_8_9_10_11_12_13_14, 7, 6",
            "1_2_3_4_5_6_7_8_9_10_11_12_13_14, 15, -1",
    })
    void shouldPerformUsualBinarySearch(String arrExpr, int toFind, int expected) {
        int[] arr = Fixtures.splitAndParseArr(arrExpr, "_");

        int index = sol.usualBS(arr, toFind);

        assertEquals(expected, index);
    }

    @ParameterizedTest
    @CsvSource({
            "1_2_3_4_5_6_7_8_9_10_11_12_13_14, 10, 9",
            "1_2_3_4_5_6_7_8_9_10_11_12_13_14, 14, 13",
            "1_2_3_4_5_6_7_8_9_10_11_12_13_14, 1, 0",
            "1_2_3_4_5_6_7_8_9_10_11_12_13_14, 7, 6",
            "1_2_3_4_5_6_7_8_9_10_11_12_13_14, 15, -1",
    })
    void shouldPerformIterativeJumpWayBinarySearch(String arrExpr, int toFind, int expected) {
        int[] arr = Fixtures.splitAndParseArr(arrExpr, "_");

        int index = sol.iterativeJumpBS(arr, toFind);

        assertEquals(expected, index);
    }

    @ParameterizedTest
    @CsvSource(value = {
            "1_1_1_2_2_2_2_2_3_3_3_4_4_4_4_5_5_5_6_6_6_6_6_6, 5, 15",
            "1_1_1_2_2_2_2_2_3_3_3_4_4_4_4_5_5_5_6_6_6_6_6_6, 1, 0",
            "1_1_1_2_2_2_2_2_3_3_3_4_4_4_4_5_5_5_6_6_6_6_6_6, 2, 3",
            "1_1_1_2_2_2_2_2_3_3_3_4_4_4_4_5_5_5_6_6_6_6_6_6, 3, 8",
            "1_1_1_2_2_2_2_2_3_3_3_4_4_4_4_5_5_5_6_6_6_6_6_6, 6, 18",
            "1_1_1_2_2_2_2_2_3_3_3_4_4_4_4_5_5_5_6_6_6_6_6_6, 7, -1",
    })
    void shouldReturnTheLowerBoundIndexOfTheKey(String arrExpr, int key, int expected) {
        int[] arr = Fixtures.splitAndParseArr(arrExpr, "_");

        int index = sol.lowerBound(arr, key);

        assertEquals(expected, index);
    }

    @ParameterizedTest
    @CsvSource(value = {
            "1_1_1_2_2_2_2_2_3_3_3_4_4_4_4_5_5_5_6_6_6_6_6_6, 5, 17",
            "1_1_1_2_2_2_2_2_3_3_3_4_4_4_4_5_5_5_6_6_6_6_6_6, 1, 2",
            "1_1_1_2_2_2_2_2_3_3_3_4_4_4_4_5_5_5_6_6_6_6_6_6, 2, 7",
            "1_1_1_2_2_2_2_2_3_3_3_4_4_4_4_5_5_5_6_6_6_6_6_6, 3, 10",
            "1_1_1_2_2_2_2_2_3_3_3_4_4_4_4_5_5_5_6_6_6_6_6_6, 6, 23",
            "1_1_1_2_2_2_2_2_3_3_3_4_4_4_4_5_5_5_6_6_6_6_6_6, 7, -1",
    })
    void shouldReturnTheUpperBoundIndexOfTheKey(String arrExpr, int key, int expected) {
        int[] arr = Fixtures.splitAndParseArr(arrExpr, "_");

        int index = sol.upperBound(arr, key);

        assertEquals(expected, index);
    }

}