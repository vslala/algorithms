package com.bma.problemsolving.leetcode.java.dfsbfs;

import java.util.Deque;
import java.util.LinkedList;

import static com.bma.problemsolving.leetcode.Model.Coordinate;

/**
 * 1091. Shortest Path in Binary Matrix
 * Given an n x n binary matrix grid, return the length of the shortest clear path in the matrix. If there is no clear path, return -1.
 * <p>
 * A clear path in a binary matrix is a path from the top-left cell (i.e., (0, 0)) to the bottom-right cell (i.e., (n - 1, n - 1)) such that:
 * <p>
 * All the visited cells of the path are 0.
 * All the adjacent cells of the path are 8-directionally connected (i.e., they are different and they share an edge or a corner).
 * The length of a clear path is the number of visited cells of this path.
 *
 * @author varun.shrivastava
 */
class ShortestPathInBinaryMatrix {
    private static final Coordinate[] allDirections = {
            new Coordinate(-1, -1),
            new Coordinate(-1, 0),
            new Coordinate(-1, 1),
            new Coordinate(0, 1),
            new Coordinate(1, 1),
            new Coordinate(1, 0),
            new Coordinate(1, -1),
            new Coordinate(0, -1),
    };

    public int shortestPathBinaryMatrix(int[][] grid) {
        final Coordinate startCoordinate = new Coordinate(0, 0);
        final Coordinate endCoordinate = new Coordinate(grid.length - 1, grid.length - 1);
        if (grid[startCoordinate.getRow()][startCoordinate.getCol()] == 1 || grid[endCoordinate.getRow()][endCoordinate.getCol()] == 1) return -1;
        if (startCoordinate.equals(endCoordinate)) {
            return 1;
        }

        Deque<Coordinate> q = new LinkedList<>();
        grid[0][0] = 1;
        q.offer(startCoordinate);

        while (!q.isEmpty()) {
            Coordinate coord = q.poll();
            int distanceSoFar = grid[coord.getRow()][coord.getCol()];

            for (Coordinate direction: allDirections) {
                Coordinate newCoord = coord.plus(direction);
                if (newCoord.isInBounds(grid) && grid[newCoord.getRow()][newCoord.getCol()] == 0) {
                    grid[newCoord.getRow()][newCoord.getCol()] = distanceSoFar + 1;
                    q.offer(newCoord);
                }

                if (newCoord.equals(endCoordinate)) {
                    return grid[newCoord.getRow()][newCoord.getCol()];
                }
            }
        }

        return -1;
    }
}
