package com.bma.problemsolving.leetcode.java.blind75;

/**
 * @author Varun Shrivastava
 * @github www.github.com/vslala
 * @date 17/05/2025
 */
class NumberOfIsland {

    private static final int[][] DIRECTION = {{-1, 0}, {0, -1}, {1, 0}, {0, 1}};

    public int numIslands(char[][] grid) {
        if (grid.length == 0) return 0;
        boolean[][] visited = new boolean[grid.length][ grid[0].length];
        int result = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (visited[i][j]) continue;
                if (grid[i][j] == '1') {
                    dfs(new int[]{i, j}, grid, visited);
                    result++;
                }
            }
        }

        return result;
    }

    private void dfs(int[] coordinate, char[][] grid, boolean[][] visited) {
        if (grid[coordinate[0]][coordinate[1]] == '0') return;
        visited[coordinate[0]][coordinate[1]] = true;

        for (int[] direction: DIRECTION) {
            int[] newCoordinate = new int[] {coordinate[0] + direction[0], coordinate[1] + direction[1] };
            if (isInBounds(newCoordinate, grid) && !visited[newCoordinate[0]][newCoordinate[1]]) {
                dfs(newCoordinate, grid, visited);
            }
        }

    }

    private boolean isInBounds(int[] coordinate, char[][] grid) {
        int row = coordinate[0];
        int col = coordinate[1];
        return row >= 0 && row < grid.length &&
                col >= 0 && col < grid[row].length;
    }
}
