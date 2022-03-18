package com.bma.problemsolving.leetcode.java.dfsbfs;

import com.bma.problemsolving.Model.Coordinate;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * 994. Rotting Oranges
 * You are given an m x n grid where each cell can have one of three values:
 *
 * 0 representing an empty cell,
 * 1 representing a fresh orange, or
 * 2 representing a rotten orange.
 * Every minute, any fresh orange that is 4-directionally adjacent to a rotten orange becomes rotten.
 *
 * Return the minimum number of minutes that must elapse until no cell has a fresh orange. If this is impossible, return -1.
 *
 * @author varun.shrivastava
 */
public class RottingOranges {

    private static final List<Coordinate> ADJ = List.of(
            new Coordinate(0, 1),
            new Coordinate(0, -1),
            new Coordinate(1, 0),
            new Coordinate(-1, 0)
    );

    public int orangesRotting(int[][] grid) {
        int freshApples = countFreshApples(grid);
        Queue<Coordinate> q = populateQueueWithRottenAppleCellCoordinate(grid);

        var level = -1;
        while (!q.isEmpty()) {
            level++;
            var size = q.size();

            for (int i = 0; i < size; i++) {
                Coordinate currCoordinate = q.poll();
                assert currCoordinate != null;

                for (Coordinate adjCell : ADJ) {
                    var newCoordinate = currCoordinate.plus(adjCell);
                    if (newCoordinate.isInBounds(grid) && grid[newCoordinate.getRow()][newCoordinate.getCol()] == 1) {
                        grid[newCoordinate.getRow()][newCoordinate.getCol()] = 2;
                        freshApples--;
                        q.add(newCoordinate);
                    }
                }
            }
        }

        if (freshApples > 0) {
            return -1;
        } else if (level == -1) {
            return 0;
        }

        return level;
    }

    private Queue<Coordinate> populateQueueWithRottenAppleCellCoordinate(int[][] grid) {
        Queue<Coordinate> q = new LinkedList<>();
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == 2) {
                    q.add(new Coordinate(i, j));
                }
            }
        }

        return q;
    }

    private int countFreshApples(int[][] grid) {
        var freshApples = 0;
        for (int[] ints : grid) {
            for (int appleType : ints) {
                if (appleType == 1) {
                    freshApples++;
                }
            }
        }

        return freshApples;
    }
}
