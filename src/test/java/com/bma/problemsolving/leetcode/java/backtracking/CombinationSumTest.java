package com.bma.problemsolving.leetcode.java.backtracking;

import com.bma.algorithms.sort.elementary.Util;
import com.bma.fixtures.Fixtures;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertTrue;

class CombinationSumTest {

    private CombinationSum combinationSum = new CombinationSum();

    @ParameterizedTest
    @CsvSource({
            "2_3_6_7, 7, 2_2_3:7",
            "2_3_5, 8, 2_2_2_2:2_3_3:3_5",
            "2, 1, _",
            "1, 2, 1_1:"
    })
    void shouldReturnListOfUniqueCombinationsThatEqualsToATargetNumber(String inputArrStr, int target, String expectedStr) {
        int[] candidates = Fixtures.splitAndParseArr(inputArrStr, "_");
        var l = expectedStr.split(":");
        var expected = new ArrayList<List<Integer>>();
        for (String s : l) {
            var arr = Fixtures.splitAndParseArr(s, "_");
            expected.add(Arrays.stream(arr).boxed().collect(Collectors.toList()));
        }

        List<List<Integer>> result = combinationSum.combinationSum(candidates, target);
        Util.println(result);
        expected.forEach(ls -> ls.forEach(num -> {
            assertTrue(result.stream().anyMatch(ls2 -> ls2.stream().anyMatch(num2 -> num2.equals(num))));
        }));

    }

}