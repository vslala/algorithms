package com.bma.problemsolving.leetcode.java.dfsbfs;

import com.bma.fixtures.Fixtures;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.Arrays;

class Matrix01Test {

    private Matrix01 sol = new Matrix01();

    @ParameterizedTest
    @CsvSource({
            "[[0_0_0]:[0_1_0]:[0_0_0]], [[0_0_0]:[0_1_0]:[0_0_0]]",
            "[[0_0_0]:[0_1_0]:[1_1_1]], [[0_0_0]:[0_1_0]:[1_2_1]]"
    })
    void shouldUpdateEachCellWithTheDistanceToItsNearestZero(String inputMatrixStr, String expectedMatrixStr) {
        var original = Fixtures.convertToPrimitiveArrMatrix(Fixtures.parseExpression(inputMatrixStr));
        var matrix = Arrays.copyOf(original, original.length);
        var expected = Fixtures.convertToPrimitiveArrMatrix(Fixtures.parseExpression(expectedMatrixStr));

        var result = sol.updateMatrix(matrix);

        Fixtures.assertBothMatrixContainsSameItems(original, expected, result);
    }

}