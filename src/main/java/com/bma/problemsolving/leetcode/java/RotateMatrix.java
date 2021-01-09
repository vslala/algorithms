package com.bma.problemsolving.leetcode.java;

import com.bma.algorithms.sort.elementary.Util;

public class RotateMatrix {
    public void rotateUsingTransposeMethod(int[][] matrix) {
//        print(matrix);
        timeIt(() -> {
            // transpose the matrix
            transpose(matrix);
//            print(matrix);

            // reverse each row  of the matrix
            reverse(matrix);
//            print(matrix);
        });
    }

    private void reverse(int[][] matrix) {
        Util.println("Reversing each row of the matrix...");
        for (int i = 0; i < matrix.length; i++) {
            int left = 0;
            int right = matrix.length - 1;

            while (left < right) {
                int temp = matrix[i][left];
                matrix[i][left] = matrix[i][right];
                matrix[i][right] = temp;

                left++;
                right--;
            }
        }
    }

    private void transpose(int[][] matrix) {
        Util.println("Transposing the matrix...");
        for (int i = 0; i  < matrix.length; i++) {
            for (int j = i; j < matrix[i].length; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
        }
    }

    private void print(int[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                Util.print(matrix[i][j] + ",");
            }
            Util.println();
        }
        Util.println();
    }

    public void timeIt(Runnable job) {
        long startTime = System.nanoTime();
        job.run();
        Util.println("Total Time Taken: "  + (System.nanoTime() - startTime) +  " nano seconds");
    }

    /**
     * DO NOT MEMORIZE
     * ==================
     * 1. start by placing the 'n' wherever you want to pick the last element of row  or column.
     * for ex:
     * Bottom left  -> matrix[n][]  , here we know that the row will be n - 1, so put the 'n' on the row array
     * Bottom Right -> matrix[n][]  , here we know that the row will be n - 1, so put the 'n' on the row array
     * Top Right    -> matrix[][n]  , here we know that the col will be n - 1, so put the 'n' on the col array
     * Top Left     -> matrix[i][j] , here we want the first of both, so there will be no 'n'
     *
     * Now we just have to figure out the position of 'i', and 'j'.
     * Bottom left  -> matrix[n - j][i] ,       here you would put `n-j`  in place of row because 'i' is definitely more than 0
     * Bottom Right -> matrix[n - i][n - j] ,   here you would put 'n-i' in row and 'n-j' in col because you know both 'i' and 'j' should definitely be more than 0 to reach that place.
     * Top Right    -> matrix[j][n - i] ,       here you would put 'n - i' in col because you know that j would definitely be more than 0 to reach top-right.
     * Top Left     -> matrix[i][j] ,           here both 'i' and 'j' will be '0' therefore we won't put n anywhere.
     *
     * @param matrix
     */
    public void rotateCellByCell(int[][] matrix) {
        timeIt(() -> {


            // Bottom left  -> matrix[n - 1 - j][i]
            // Bottom Right -> matrix[n - 1 - i][n - j - 1]
            // Top Right    -> matrix[j][n - 1 -i]
            // Top Left     -> matrix[i][j]
            int  n = matrix.length - 1;

            for (int i = 0; i < (matrix.length + 1) / 2; i++) {
                for (int j = 0; j < matrix.length/2; j++) {
                    int temp = matrix[n - j][i];
                    matrix[n - j][i] = matrix[n - i][n - j];
                    matrix[n - i][n - j] = matrix[j][n - i];
                    matrix[j][n - i] = matrix[i][j];
                    matrix[i][j] = temp;
                }
            }


        });

    }
}
