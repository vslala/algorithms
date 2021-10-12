package com.bma.problemsolving.leetcode.java.backtracking;

import com.bma.algorithms.sort.elementary.Util;
import com.bma.fixtures.Fixtures;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class CombinationSumTwoTest {

    private CombinationSumTwo sol = new CombinationSumTwo();

    @ParameterizedTest
    @CsvSource({
            "10_1_2_7_6_1_5, 8, [[1_1_6]:[1_2_5]:[1_7]:[2_6]]",
            "2_5_2_1_2, 5, [[1_2_2]:[5]]",
    })
    void shouldReturnUniqueCombinationOfSetThatSumsUpToATargetValue(String inputStr, int target, String expected) {
        var candidates = Fixtures.splitAndParseArr(inputStr, "_");
        var result = sol.combinationSum2(candidates, target);

        Util.println(result);

        Fixtures.assertBothListsContainsSameItems(Fixtures.parseExpression(expected), result);
    }
}