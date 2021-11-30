package com.bma.problemsolving.leetcode.java.dfsbfs;

/**
 * 130. Surrounded Regions
 * Given an m x n matrix board containing 'X' and 'O', capture all regions that are 4-directionally surrounded by 'X'.
 * A region is captured by flipping all 'O's into 'X's in that surrounded region.
 * @author varun.shrivastava
 */
class SurroundedRegion {
    public void surroundRegion(char[][] board) {
        for (int r = 0; r < board.length; r++) {
            if (board[r][0] == 'O') {
                surroundRegion(board, r, 0);
            }

            if (board[r][board[r].length - 1] == 'O') {
                surroundRegion(board, r, board[r].length - 1);
            }
        }

        for (int c = 0; c < board[0].length; c++) {
            if (board[0][c] == 'O') {
                surroundRegion(board, 0, c);
            }

            if (board[board.length - 1][c] == 'O') {
                surroundRegion(board, board.length - 1, c);
            }
        }

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] == 'O') {
                    board[i][j] = 'X';
                }

                if (board[i][j] == '$') {
                    board[i][j] = 'O';
                }
            }
        }
    }

    private void surroundRegion(char[][] board, int i, int j) {
        if (isOutOfBounds(board, i, j) || board[i][j] != 'O') {
            return;
        }
        System.out.println(String.format("%d, %d", i, j));
        board[i][j] = '$';
        surroundRegion(board, i + 1, j);
        surroundRegion(board, i - 1, j);
        surroundRegion(board, i, j + 1);
        surroundRegion(board, i, j - 1);
    }

    boolean isOutOfBounds(char[][] board, int i, int j) {
        if (i < 0 || i >= board.length || j < 0 || j >= board[i].length) {
            return true;
        }

        return false;
    }
}
