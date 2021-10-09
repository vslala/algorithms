package com.bma.problemsolving.leetcode.java.dynamicprogramming;

/**
 * 63. Unique Paths II
 * A robot is located at the top-left corner of a m x n grid (marked 'Start' in the diagram below).
 * The robot can only move either down or right at any point in time. The robot is trying to reach the bottom-right corner of the grid (marked 'Finish' in the diagram below).
 * Now consider if some obstacles are added to the grids. How many unique paths would there be?
 * An obstacle and space is marked as 1 and 0 respectively in the grid.
 *
 * @author varun.shrivastava
 */
public class UniquePathsTwoWithObstacles {

    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int m = obstacleGrid.length;
        if (m == 0) return 0;

        int n = obstacleGrid[0].length;

        if (obstacleGrid[0][0] == 1) return 0;
        if (obstacleGrid[m - 1][n - 1] == 1)
            return 0;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                if (obstacleGrid[i][j] == 1)
                    obstacleGrid[i][j] = -1;
            }
        }

        for (int i = 0; i < m; i++) {
            if (obstacleGrid[i][0] == -1) break;
            if (obstacleGrid[i][0] == 0) {
                obstacleGrid[i][0] = 1;    // fill first column with 1s
            }
        }

        for (int j = 0; j < n; j++) {
            if (obstacleGrid[0][j] == -1) break;
            if (obstacleGrid[0][j] == 0) obstacleGrid[0][j] = 1;    // fill first row
        }

        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                if (obstacleGrid[i][j] == -1) continue;

                if (obstacleGrid[i - 1][j] == -1 && obstacleGrid[i][j - 1] == -1) { // top and left is obstacle
                    obstacleGrid[i][j] = 0;
                    continue;
                }

                if (obstacleGrid[i][j - 1] == -1) { // left is obstacle
                    obstacleGrid[i][j] = obstacleGrid[i - 1][j];
                    continue;
                }

                if (obstacleGrid[i - 1][j] == -1) { // top is obstacle
                    obstacleGrid[i][j] = obstacleGrid[i][j - 1];
                    continue;
                }

                obstacleGrid[i][j] = obstacleGrid[i - 1][j] + obstacleGrid[i][j - 1];
            }
        }

        return obstacleGrid[m - 1][n - 1];
    }
}
