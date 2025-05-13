package com.bma.problemsolving.leetcode.java.blind75;

import com.bma.fixtures.Fixtures;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class RotateImageTest {

    @ParameterizedTest
    @CsvSource(value = {
            "[[5,1,9,11],[2,4,8,10],[13,3,6,7],[15,14,12,16]] [[15,13,2,5],[14,3,4,1],[12,6,8,9],[16,7,10,11]]"
    }, delimiter = ' ')
    void given_a_2d_matrix_it_should_rotate_the_image_90_degrees_right(String inputMatrix, String expectedMatrix) {
        int[][] matrix = Fixtures.convertToPrimitiveArrMatrix(Fixtures.parseNestedArrExpression(inputMatrix, Integer.class));
        int[][] expected = Fixtures.convertToPrimitiveArrMatrix(Fixtures.parseNestedArrExpression(expectedMatrix, Integer.class));
        var original = matrix;

        var sol = new RotateImage();
        sol.rotate(matrix);

        Fixtures.assertBothMatrixContainsSameItems(original, expected, matrix);
    }
}