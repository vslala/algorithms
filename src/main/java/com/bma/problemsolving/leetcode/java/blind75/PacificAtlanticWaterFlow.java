package com.bma.problemsolving.leetcode.java.blind75;

import com.bma.problemsolving.Model;

import java.util.*;

class PacificAtlanticWaterFlow {
    public List<List<Integer>> pacificAtlantic(int[][] heights) {
        if (heights.length == 0 || heights[0].length == 0) {
            return List.of();
        }

        int m = heights.length;
        int n = heights[0].length;

        var pacific = new LinkedList<Model.Coordinate>();
        var atlantic = new LinkedList<Model.Coordinate>();

        // pacific-left most column and atlantic-right most column
        for (int r = 0; r < m; r++) {
            pacific.offer(new Model.Coordinate(r, 0));
            atlantic.offer(new Model.Coordinate(r, n - 1));
        }

        // pacific-top most column and atlantic-bottom most column
        for (int c = 0; c < n; c++) {
            pacific.offer(new Model.Coordinate(0, c));
            atlantic.offer(new Model.Coordinate(m - 1, c));
        }

        Set<Model.Coordinate> pacificReachable = bfs(heights, pacific);
        Set<Model.Coordinate> atlanticReachable = bfs(heights, atlantic);

        var result = new ArrayList<List<Integer>>();
        for (Model.Coordinate coord : pacificReachable) {
            if (atlanticReachable.contains(coord)) {
                result.add(List.of(coord.row(), coord.col()));
            }
        }

        return result;
    }

    /**
     * Traverse uphill and mark the coordinates visited
     *
     * @param heights Height of each coordinate in a grid
     * @param q Queue that holds the starting coodinates
     * @return the visited coordinates
     */
    private Set<Model.Coordinate> bfs(int[][] heights, LinkedList<Model.Coordinate> q) {
        var visited = new HashSet<Model.Coordinate>();

        while (!q.isEmpty()) {
            Model.Coordinate curr = q.poll();
            visited.add(curr);

            List<Model.Coordinate> neighbours = List.of(
                    curr.top(), curr.bottom(), curr.left(), curr.right()
            );
            for (Model.Coordinate neighbor : neighbours) {
                if (neighbor.isInBounds(heights) &&
                        !visited.contains(neighbor) &&
                        neighbor.value(heights) >= curr.value(heights)) {

                    visited.add(neighbor);
                    q.offer(neighbor);
                }
            }
        }

        return visited;
    }
}
