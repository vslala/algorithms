package com.bma.problemsolving.leetcode.java.blind75;

import java.util.HashSet;

/**
 * @author Varun Shrivastava
 * @github www.github.com/vslala
 * @date 17/05/2025
 */
class WordSearch {

    public boolean exist(char[][] board, String word) {
        var visited = new HashSet<String>();
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                visited.clear();
                if (board[i][j] == word.charAt(0) && dfs(board, word, 0, i, j, visited)) {
                    return true;
                }
            }
        }

        return false;
    }

    private boolean dfs(char[][] board, String word, int c, int i, int j, HashSet<String> visited) {
        String coordinate = concat(i, j);
        if (c == word.length()) {
            return true; // word found
        }

        if (outOfBounds(board, i, j)) {
            return false;
        }


        if (board[i][j] != word.charAt(c)) {
            return false;
        }

        if (visited.contains(coordinate)) {
            return false; // cycle found
        }

        visited.add(coordinate);
        boolean found = dfs(board, word, c + 1, i - 1, j, visited)
                || dfs(board, word, c + 1, i, j - 1, visited)
                || dfs(board, word, c + 1, i + 1, j, visited)
                || dfs(board, word, c + 1, i, j + 1, visited);
        visited.remove(coordinate);
        return found;
    }

    private boolean outOfBounds(char[][] board, int row, int col) {
        return row < 0 || row >= board.length || col < 0 || col >= board[row].length;
    }

    private String concat(int a, int b) {
        return a + ":" + b;
    }
}
