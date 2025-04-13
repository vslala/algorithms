package com.bma.problemsolving.leetcode.java;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class RotateMatrixTest {

    @Test
    void givenAMatrixOfSizeNRotateItClockWise90Degree() {
        var test = new RotateMatrix();
        var matrix = new int[4][4];
        matrix[0] = new int[]{5,1,9,11};
        matrix[1] = new int[]{2,4,8,10};
        matrix[2] = new int[]{13,3,6,7};
        matrix[3] = new int[]{15,14,12,16};
        test.rotateUsingTransposeMethod(matrix);

        assertEquals(15, matrix[0][0]);
    }

    @Test
    void givenAMatrixOfSizeNRotateItClockWise90DegreeUsingCellByCellSwap() {
        var test = new RotateMatrix();
        var matrix = new int[4][4];
        matrix[0] = new int[]{5,1,9,11};
        matrix[1] = new int[]{2,4,8,10};
        matrix[2] = new int[]{13,3,6,7};
        matrix[3] = new int[]{15,14,12,16};

        test.rotateCellByCell(matrix);
        assertEquals(15, matrix[0][0]);
    }

}