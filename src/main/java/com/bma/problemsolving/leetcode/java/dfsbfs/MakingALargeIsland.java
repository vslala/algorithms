package com.bma.problemsolving.leetcode.java.dfsbfs;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static com.bma.problemsolving.Model.Coordinate;

/**
 * 827. Making A Large Island
 * <p>
 * You are given an n x n binary matrix grid. You are allowed to change at most one 0 to be 1.
 * Return the size of the largest island in grid after applying this operation.
 * An island is a 4-directionally connected group of 1s.
 *
 * @author varun.shrivastava
 */
class MakingALargeIsland {

    private List<Coordinate> directions = List.of(
            new Coordinate(-1, 0),
            new Coordinate(1, 0),
            new Coordinate(0, -1),
            new Coordinate(0, 1)
    );

    public int largestIsland(int[][] grid) {
        Map<Integer, Integer> islandMap = constructIslandMap(grid);

        // this will iterate over all values of map to get the max island size
        int best = islandMap.keySet()
                .stream()
                .map(islandMap::get)
                .max(Integer::compare)
                .orElse(0);

        // iterate over grid one more time to turn '0' to '1' and check the island size it creates
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == 0) {
                    Coordinate coordinate = new Coordinate(i, j);

                    // check up, down, right, left for island id
                    // if island id is not 0 then add it to the distinctSurroundingIslandIds
                    Set<Integer> distinctSurroundingIslandIds = directions
                            .stream()
                            .map(dir -> dir.plus(coordinate)) // up, down, right, left
                            .filter(coord -> coord.isInBounds(grid))
                            .map(coord -> grid[coord.getRow()][coord.getCol()])
                            .filter(value -> value != 0)    // value shouldn't be zero
                            .collect(Collectors.toSet());   // using a distinctSurroundingIslandIds to avoid duplicate island ids

                    // iterate over surrounding islandIds
                    // and sum their size
                    int sum = distinctSurroundingIslandIds
                            .stream()
                            .map(islandMap::get)
                            .reduce(1, Integer::sum);

                    best = Math.max(sum, best);
                }

            }
        }

        return best;
    }

    private Map<Integer, Integer> constructIslandMap(int[][] grid) {
        Map<Integer, Integer> map = new HashMap<>();
        int islandId = 2; // because there could only be 1 or 0 in the input grid
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                int size = islandSize(grid, new Coordinate(i, j), islandId);
                map.put(islandId++, size);
            }
        }

        return map;
    }

    private int islandSize(int[][] grid, Coordinate coord, int islandId) {
        // if the cell contains 0 or any other island id other than 1 then return 0
        int row = coord.getRow();
        int col = coord.getCol();
        if (!coord.isInBounds(grid) || grid[row][col] != 1) return 0;
        grid[row][col] = islandId;

        int a = islandSize(grid, coord.plusRow(1), islandId);
        int b = islandSize(grid, coord.minusRow(1), islandId);
        int c = islandSize(grid, coord.plusCol(1), islandId);
        int d = islandSize(grid, coord.minusCol(1), islandId);

        // the last one is to account for the current cell that is changed in line 48
        return a + b + c + d + 1;
    }
}
