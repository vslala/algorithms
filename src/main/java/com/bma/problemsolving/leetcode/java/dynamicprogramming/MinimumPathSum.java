package com.bma.problemsolving.leetcode.java.dynamicprogramming;

/**
 * 64. Minimum Path Sum
 * Given a m x n grid filled with non-negative numbers, find a path from top left to bottom right,
 * which minimizes the sum of all numbers along its path.
 * <p>
 * Note: You can only move either down or right at any point in time.
 *
 * @author varun.shrivastava
 */
class MinimumPathSum {
    public int minPathSum(int[][] grid) {
        if (grid.length == 0) {
            return 0;
        }

        for (var i = 0; i < grid.length; i++) {
            updateMinSum(grid, i, 0);
        }

        return grid[grid.length - 1][grid[0].length - 1];
    }

    private void updateMinSum(int[][] grid, int i, int j) {
        if (isNotInBounds(grid, i, j)) {
            return;
        }

        if (isNotInBounds(grid, i - 1, j) && isNotInBounds(grid, i, j - 1)) {
            updateMinSum(grid, i, j + 1);
            return;
        }

        if (isNotInBounds(grid, i - 1, j)) {
            grid[i][j] = grid[i][j - 1] + grid[i][j];
            updateMinSum(grid, i, j + 1);
            return;
        }

        if (isNotInBounds(grid, i, j - 1)) {
            grid[i][j] = grid[i - 1][j] + grid[i][j];
            updateMinSum(grid, i, j + 1);
            return;
        }

        if (isInBounds(grid, i - 1, j) && isInBounds(grid, i, j - 1)) {
            grid[i][j] = Math.min(grid[i][j - 1] + grid[i][j], grid[i - 1][j] + grid[i][j]);
            updateMinSum(grid, i, j + 1);
        }
    }

    private boolean isInBounds(int[][] grid, int i, int j) {
        return !isNotInBounds(grid, i, j);
    }

    private boolean isNotInBounds(int[][] grid, int i, int j) {
        return i < 0 || i >= grid.length || j < 0 || j >= grid[i].length;
    }

}
