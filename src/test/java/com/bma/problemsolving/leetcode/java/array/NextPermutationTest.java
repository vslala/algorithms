package com.bma.problemsolving.leetcode.java.array;

import com.bma.fixtures.Fixtures;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class NextPermutationTest {

    private NextPermutation sol = new NextPermutation();

    @ParameterizedTest
    @CsvSource(value = {
            "[[1,2,3]] [[1,3,2]]",
            "[[3,2,1]] [[1,2,3]]",
            "[[1,1,5]] [[1,5,1]]",
            "[[1]] [[1]]",
            "[[1,1]] [[1,1]]"
    }, delimiter = ' ')
    void shouldReturnANextLexicographicalPermutationOfTheProvidedSequence(String inputExpr, String expectedExpr) {
        int[] input = Fixtures.convertToPrimitiveArrMatrix(Fixtures.parseNestedArrExpression(inputExpr, Integer.class))[0];
        int[] expected = Fixtures.convertToPrimitiveArrMatrix(Fixtures.parseNestedArrExpression(expectedExpr, Integer.class))[0];

        sol.nextPermutation(input);

        Fixtures.assertArrayEquals(input, expected, input);
    }
}