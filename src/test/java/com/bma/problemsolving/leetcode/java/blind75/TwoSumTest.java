package com.bma.problemsolving.leetcode.java.blind75;

import com.bma.problemsolving.leetcode.java.LeetCodeInputExpressionParser;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.Arrays;
import java.util.List;

class TwoSumTest {

    private TwoSum sol = new TwoSum();

    @ParameterizedTest
    @CsvSource(value = {
            "[[2,7,11,15]] 9 [[0,1]]",
            "[[3,2,4]] 6 [[1,2]]",
            "[[3,3]] 6 [[0,1]]"
    }, delimiter = ' ')
    void is_should_return_the_indices_of_numbers_that_adds_up_to_target_sum(String arrInput, int targetSum, String arrExpected) {
        List<List<Integer>> input = LeetCodeInputExpressionParser.parseNestedArrExpression(arrInput, ',', Integer.class);
        int[] output = sol.twoSum(input.getFirst().stream().mapToInt(Integer::intValue).toArray(), targetSum);

        List<List<Integer>> expectedArr = LeetCodeInputExpressionParser.parseNestedArrExpression(arrExpected, ',', Integer.class);
        int[] expected = expectedArr.getFirst().stream().mapToInt(Integer::intValue).toArray();

        Arrays.sort(output);
        Arrays.sort(expected);
        Assertions.assertArrayEquals(expected, output);
    }
}