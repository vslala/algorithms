package com.bma.problemsolving.leetcode.java;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SetMatrixZeroesTest {

    @Test
    void givenAnMxNMatrixIfAnElementIs0SetItsEntireRowAndColumnTo0() {
        var test = new SetMatrixZeroes();
        int[][] matrix = new int[3][3];
        matrix[0] = new int[]{1,1,1};
        matrix[1] = new int[]{1,0,1};
        matrix[2] = new int[]{1,1,1};

        test.setZeroes(matrix);

        assertEquals(0, matrix[1][0]);
        assertEquals(0, matrix[0][1]);
        assertEquals(0, matrix[1][2]);
        assertEquals(0, matrix[2][1]);
    }
}