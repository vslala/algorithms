package com.bma.problemsolving.leetcode.java.dfsbfs;

/**
 * 695. Max Area of Island
 * You are given an m x n binary matrix grid. An island is a group of 1's (representing land) connected 4-directionally (horizontal or vertical.)
 * You may assume all four edges of the grid are surrounded by water.
 * The area of an island is the number of cells with a value 1 in the island.
 * Return the maximum area of an island in grid. If there is no island, return 0.
 *
 * @author varun.shrivastava
 */
public class MaxAreaOfIsland {

    public int maxAreaOfIslandDfs(int[][] grid) {
        var result = Integer.MIN_VALUE;
        for (int r = 0; r < grid.length; r++) {
            for (int c = 0; c < grid[r].length; c++) {
                result = Math.max(result, area(grid, r, c));
            }
        }
        return result;
    }

    private int area(int[][] grid, int r, int c) {
        if (r < 0 || r >= grid.length || c < 0 || c >= grid[r].length || grid[r][c] == 0) {
            return 0;
        }

        grid[r][c] = 0;
        return 1 + area(grid, r + 1, c) +
                area(grid, r - 1, c) +
                area(grid, r, c + 1) +
                area(grid, r, c - 1);
    }
}
