package com.bma.problemsolving.leetcode.java.contest;

import com.bma.algorithms.sort.elementary.Util;
import com.bma.fixtures.Fixtures;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertTrue;

class WeeklyContest262Test {

    private WeeklyContest262 contest = new WeeklyContest262();

    @ParameterizedTest
    @CsvSource({
            "1_1_3_2,2_3,3_3, 3_2",
            "3_1,2_3,1_2, 2_3_1",
            "1_2_2,4_3_3,5, _"
    })
    void shouldReturnDistinctArrayContainingAllValuesAtleastPresentInTwoOutOfThree(String arr1, String arr2, String arr3, String expected) {
        var nums1 = Fixtures.splitAndParseArr(arr1, "_");
        var nums2 = Fixtures.splitAndParseArr(arr2, "_");
        var nums3 = Fixtures.splitAndParseArr(arr3, "_");
        var expectedOutput = Fixtures.splitAndParseArr(expected, "_");

        var result = contest.twoOutOfThree(nums1, nums2, nums3);
        for (int val : expectedOutput) {
            Util.println(result + ":" + val);
            assertTrue(result.contains(val));
        }
    }

}