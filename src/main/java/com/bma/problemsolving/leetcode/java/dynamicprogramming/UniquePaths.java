package com.bma.problemsolving.leetcode.java.dynamicprogramming;

/**
 * 62. Unique Paths
 * A robot is located at the top-left corner of a m x n grid (marked 'Start' in the diagram below).
 * The robot can only move either down or right at any point in time. The robot is trying to reach the bottom-right corner of the grid (marked 'Finish' in the diagram below).
 * How many possible unique paths are there?
 *
 * @author varun.shrivastava
 */
public class UniquePaths {

    public int uniquePaths(int m, int n) {
        var dp = new int[m][n];
        // fill first column with 1
        for (int i = 0; i < m; i++) {
            dp[i][0] = 1;
        }

        // fill first row with 1
        for (int j = 0; j < n; j++) {
            dp[0][j] = 1;
        }

        // iteratively add total from above cell and left cell
        for (int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++) {
                dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
            }
        }

        return dp[m - 1][n - 1];
    }

    public int findUniquePaths(int m, int n) {
        if (m == 1 || n == 1) {
            return 1;
        }

        return findUniquePaths(m - 1, n) + findUniquePaths(m, n - 1);
    }
}
