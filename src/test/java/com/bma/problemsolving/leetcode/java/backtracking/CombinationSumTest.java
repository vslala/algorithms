package com.bma.problemsolving.leetcode.java.backtracking;

import com.bma.algorithms.sort.elementary.Util;
import com.bma.fixtures.Fixtures;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.List;

class CombinationSumTest {

    private CombinationSum combinationSum = new CombinationSum();

    @ParameterizedTest
    @CsvSource({
            "2_3_6_7, 7, [[2_2_3]:[7]]",
            "2_3_5, 8, [[2_2_2_2]:[2_3_3]:[3_5]]",
            "2, 1, []",
            "1, 2, [[1_1]]"
    })
    void shouldReturnListOfUniqueCombinationsThatEqualsToATargetNumber(String inputArrStr, int target, String expectedStr) {
        int[] candidates = Fixtures.splitAndParseArr(inputArrStr, "_");
        var l = expectedStr.split(":");
        List<List<Integer>> expected = Fixtures.parseExpression(expectedStr);

        List<List<Integer>> result = combinationSum.combinationSum(candidates, target);
        Util.println(result);
        Fixtures.assertBothListsContainsSameItems(expected, result);
    }

}