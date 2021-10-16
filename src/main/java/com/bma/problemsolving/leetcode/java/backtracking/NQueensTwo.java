package com.bma.problemsolving.leetcode.java.backtracking;

import java.util.Arrays;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 52. N-Queens II
 * The n-queens puzzle is the problem of placing n queens on an n x n chessboard such that no two queens attack each other.
 * Given an integer n, return the number of distinct solutions to the n-queens puzzle.
 *
 * @author varun.shrivastava
 */
class NQueensTwo {
    public int totalNQueens(int n) {
        var result = new AtomicInteger();
        var chessBoard = new char[n][n];
        for (char[] chars : chessBoard) {
            Arrays.fill(chars, '.');
        }
        solveForQueens(chessBoard, 0, result);
        return result.get();
    }

    void solveForQueens(char[][] chessBoard, int row, AtomicInteger result) {
        if (row == chessBoard.length) {
            result.getAndIncrement();
            return;
        }

        for (var col = 0; col < chessBoard.length; col++) {
            if (isSafeToPlaceQueen(chessBoard, row, col)) {
                chessBoard[row][col] = 'Q';
                solveForQueens(chessBoard, row + 1, result);
                chessBoard[row][col] = '.';
            }
        }
    }

    private boolean isSafeToPlaceQueen(char[][] chessBoard, int row, int col) {
        {
            // check for vertical
            for (int i = row; i >= 0; i--) {
                if (chessBoard[i][col] == 'Q') {
                    return false;
                }
            }
        }
        {
            // check for left diagonal
            for (int i = row, j = col; i >= 0 && j >= 0; i--, j--) {
                if (chessBoard[i][j] == 'Q') {
                    return false;
                }
            }
        }
        {
            // check for right diagonal
            for (int i = row, j = col; i >= 0 && j < chessBoard.length; i--, j++) {
                if (chessBoard[i][j] == 'Q') {
                    return false;
                }
            }
        }

        return true;
    }
}
