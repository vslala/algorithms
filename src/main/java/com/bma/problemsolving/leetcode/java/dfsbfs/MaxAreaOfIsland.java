package com.bma.problemsolving.leetcode.java.dfsbfs;

import com.bma.problemsolving.Model;

import java.util.LinkedList;

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

    /**
     * Uses Depth First Traversal to visit every coordinate of the island
     * @param grid matrix
     * @return max area of the island
     */
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

    /**
     * Uses Breadth First Traversal to visit every coordinate of the island
     * @param grid matrix
     * @return max area of the island
     */
    public int maxAreaOfIslandBfs(int[][] grid) {
        var result = 0;
        var q = new LinkedList<Model.Coordinate>();

        for (int r = 0; r < grid.length; r++) {
            for (int c = 0; c < grid[r].length; c++) {
                if (grid[r][c] == 1) {
                    q.add(new Model.Coordinate(r, c));

                    result = Math.max(result, findNumberOfAdjacentCoordinates(grid, q));
                }
            }
        }

        return result;
    }

    private int findNumberOfAdjacentCoordinates(int[][] grid, LinkedList<Model.Coordinate> q) {
        var count = 0;
        while (!q.isEmpty()) {
            var curr = q.pollFirst();
            if (curr.isInBounds(grid)
                    && grid[curr.getRow()][curr.getCol()] == 1) {
                count++;
                grid[curr.getRow()][curr.getCol()] = 0;
                q.add(curr.minusRow(1));
                q.add(curr.plusRow(1));
                q.add(curr.minusCol(1));
                q.add(curr.plusCol(1));
            }
        }

        return count;
    }

}
