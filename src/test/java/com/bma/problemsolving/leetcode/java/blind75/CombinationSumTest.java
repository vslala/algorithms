package com.bma.problemsolving.leetcode.java.blind75;

import com.bma.fixtures.Fixtures;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.List;

class CombinationSumTest {
    @ParameterizedTest
    @CsvSource(value = {
            "[2,3,6,7] 7 [[2,2,3],[7]]",
            "[2,3,5] 8 [[2,2,2,2],[2,3,3],[3,5]]"
    }, delimiter = ' ')
    void given_an_array_of_distinct_integers_and_a_target_integer_it_should_return_unique_combination_of_all_numbers_whose_sum_equals_target(String inputExpr, int target, String expectedExpr) {
        int[] input = Fixtures.parse1DArray(inputExpr);
        List<List<Integer>> expected = Fixtures.parseNestedArrExpression(expectedExpr, Integer.class);

        var sol = new CombinationSum();
        List<List<Integer>> output = sol.combinationSum(input, target);

        Fixtures.assertBothListsContainsSameItems(expected, output);
    }
}