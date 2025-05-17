package com.bma.problemsolving.leetcode.java.blind75;

import java.util.Arrays;

/**
 * @author Varun Shrivastava
 * @github www.github.com/vslala
 * @date 16/05/2025
 */
class UniquePaths {
    public int uniquePaths(int m, int n) {
        int[][] dp = new int[m][n];

        // fill first row
        Arrays.fill(dp[0], 1);

        // fill first column
        for (int r = 0; r < dp.length; r++) {
            dp[r][0] = 1;
        }

        for (int i = 1; i < dp.length; i++) {
            for (int j = 1; j < dp[i].length; j++) {
                dp[i][j] = dp[i - 1][j] + dp[i][j - 1];
            }
        }

        return dp[m - 1][n - 1];
    }
}
