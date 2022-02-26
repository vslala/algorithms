package com.bma.problemsolving.leetcode.java.array;

import com.bma.fixtures.Fixtures;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static com.bma.fixtures.Fixtures.convertToPrimitiveArrMatrix;
import static com.bma.fixtures.Fixtures.parseNestedArrExpression;

class NearestSmallerElementTest {

    private NearestSmallerElement sol = new NearestSmallerElement();

    @ParameterizedTest
    @CsvSource(value = {
            "[[1,3,4,2,5,3,4,2]] [[-1,1,3,1,2,2,3,1]]",
            "[[0,20,5,100,200,300,2,1]] [[-1,0,0,5,100,200,0,0]]",
    }, delimiter = ' ')
    void shouldFindTheNearestSmallerElementForEveryElementInTheArray(String inputExpr, String expectedExpr) {
        int[] arr = convertToPrimitiveArrMatrix(parseNestedArrExpression(inputExpr, Integer.class))[0];
        int[] expected = convertToPrimitiveArrMatrix(parseNestedArrExpression(expectedExpr, Integer.class))[0];

        int[] output = sol.nearestSmaller(arr);

        Fixtures.assertArrayEquals(arr, expected, output);
    }
}