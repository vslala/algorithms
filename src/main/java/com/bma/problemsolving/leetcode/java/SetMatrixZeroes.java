package com.bma.problemsolving.leetcode.java;

import com.bma.algorithms.sort.elementary.Util;

import java.util.ArrayList;
import java.util.Arrays;

public class SetMatrixZeroes {

    private static class Coordinates {
        int r;
        int c;

        public Coordinates(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

    public void setZeroes(int[][] matrix) {
        var zeroPoints = new ArrayList<Coordinates>();
        for (int r = 0; r < matrix.length; r++)
            for (int c = 0; c < matrix[r].length; c++)
                if (matrix[r][c] == 0) {
                    zeroPoints.add(new Coordinates(r, c));
                }


        zeroPoints.forEach(coord -> {
            // set row to 0
            Arrays.fill(matrix[coord.r], 0);
            // set col to 0
            for (int j = 0; j < matrix.length; j++) matrix[j][coord.c] = 0;
        });

    }
}
