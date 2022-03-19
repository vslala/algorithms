package com.bma.problemsolving.leetcode.java.array;

import com.bma.fixtures.Fixtures;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class ProductOfArrayExceptSelfTest {

    private ProductOfArrayExceptSelf sol = new ProductOfArrayExceptSelf();

    @ParameterizedTest
    @CsvSource(value = {
            "[[1,2,3,4]] [[24,12,8,6]]",
            "[[-1,1,0,-3,3]] [[0,0,9,0,0]]"
    }, delimiter = ' ')
    void shouldReturnTheProductOfArrayExceptSelf(String inputExpr, String expectedExpr) {
        int[] nums = Fixtures.convertToPrimitiveArrMatrix(Fixtures.parseNestedArrExpression(inputExpr, Integer.class))[0];
        int[] expected = Fixtures.convertToPrimitiveArrMatrix(Fixtures.parseNestedArrExpression(expectedExpr, Integer.class))[0];

        int[] output = sol.productExceptSelf(nums);
        int[] secondOutput = sol.productExceptSelfWithoutUsingExtraSpace(nums);

        Fixtures.assertArrayEquals(nums, expected, secondOutput);
        Fixtures.assertArrayEquals(nums, expected, output);
    }
}