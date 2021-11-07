package com.bma.problemsolving.leetcode.java.dfsbfs;

import com.bma.problemsolving.leetcode.Model.Coordinate;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.atomic.AtomicInteger;

import static java.util.Objects.nonNull;

/**
 * 542. 01 Matrix
 * Given an m x n binary matrix mat, return the distance of the nearest 0 for each cell.
 * The distance between two adjacent cells is 1.
 *
 * @author varun.shrivastava
 */
public class Matrix01 {

    private static final List<Coordinate> ADJ = List.of(
            new Coordinate(0, 1),
            new Coordinate(0, -1),
            new Coordinate(1, 0),
            new Coordinate(-1, 0)
    );

    public int[][] updateMatrix(int[][] mat) {
        Queue<Coordinate> q = populateQueueWithCoordinatesContainingZero(mat);

        var level = new AtomicInteger(0);
        while (!q.isEmpty()) {
            level.getAndIncrement();
            var size = q.size();

            for (int i = 0; i < size; i++) {
                Coordinate currCoordinate = q.poll();
                assert nonNull(currCoordinate);

                // this will visit all the adjacent cells to the current coordinate
                // and if the new cell is in bounds
                // and contains value as -1
                // then set the level for that cell
                // and add it to the queue for further processing
                ADJ.stream()
                        .map(currCoordinate::plus)
                        .filter(coordinate -> coordinate.isInBounds(mat))
                        .filter(coordinate -> mat[coordinate.getRow()][coordinate.getCol()] == -1)
                        .forEach(coordinate -> {
                            mat[coordinate.getRow()][coordinate.getCol()] = level.intValue();
                            q.add(coordinate);
                        });
            }
        }

        return mat;
    }

    private Queue<Coordinate> populateQueueWithCoordinatesContainingZero(int[][] mat) {
        var q = new LinkedList<Coordinate>();

        for (int i = 0; i < mat.length; i++) {
            for (int j = 0; j < mat[i].length; j++) {
                if (mat[i][j] == 0) {
                    q.add(new Coordinate(i, j));
                } else {
                    mat[i][j] = -1;
                }
            }
        }

        return q;
    }
}
