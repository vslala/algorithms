package com.bma.problemsolving.leetcode.java.blind75;

class RotateImage {
    public void rotate(int[][] matrix) {
        transpose(matrix);
        reflect(matrix);
    }

    private void transpose(int[][] matrix) {
        for (int r = 0; r < matrix.length; r++) {
            for (int c = r + 1; c < matrix.length; c++) {
                int tmp = matrix[c][r];
                matrix[c][r] = matrix[r][c];
                matrix[r][c] = tmp;
            }
        }
    }

    private void reflect(int[][] matrix) {
        int n = matrix.length - 1;
        for (int r = 0; r <= n; r++) {
            for (int c = 0; c <= n / 2; c++) {
                int tmp = matrix[r][c];
                matrix[r][c] = matrix[r][n - c];
                matrix[r][n - c] = tmp;
            }
        }
    }
}
