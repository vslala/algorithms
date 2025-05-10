package com.bma.problemsolving.leetcode.java.blind75;

import com.bma.algorithms.sort.elementary.Util;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.List;

import static com.bma.fixtures.Fixtures.*;

class ThreeSumTest {

    @ParameterizedTest
    @CsvSource(value = {
            "[[-1,0,1,2,-1,-4]] [[-1,-1,2],[-1,0,1]]",
            "[[0,0,0]] [[0,0,0]]",
            "[[0,1,1]] _",
            "[[2,-3,0,-2,-5,-5,-4,1,2,-2,2,0,2,-4,5,5,-10]] [[-10,5,5],[-5,0,5],[-4,2,2],[-3,-2,5],[-3,1,2],[-2,0,2]]"
    }, delimiter = ' ')
    void it_should_return_all_triplets_whose_sum_is_zero(String inputExpr, String expectedExpr) {
        int[] input = convertToPrimitiveArrMatrix(parseNestedArrExpression(inputExpr, Integer.class))[0];
        List<List<Integer>> expected = expectedExpr.equals("_") ? List.of() : parseNestedArrExpression(expectedExpr, Integer.class);

        Util.println(input);
        Util.println(expected);
        var sol = new ThreeSum();
        List<List<Integer>> output = sol.threeSum(input);

        assertBothNestedListsContainsSameItems(expected, output);
        Assertions.assertEquals(expected.size(), output.size());
    }

}