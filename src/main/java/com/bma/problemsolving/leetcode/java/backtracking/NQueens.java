package com.bma.problemsolving.leetcode.java.backtracking;

import com.bma.algorithms.sort.elementary.Util;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 51. N-Queens
 * The n-queens puzzle is the problem of placing n queens on an n x n chessboard such that no two queens attack each other.
 * Given an integer n, return all distinct solutions to the n-queens puzzle. You may return the answer in any order.
 * Each solution contains a distinct board configuration of the n-queens' placement, where 'Q' and '.' both indicate a queen and an empty space, respectively.
 *
 * @author varun.shrivastava
 */
class NQueens {
    public List<List<String>> solveNQueens(int n) {
        var result = new ArrayList<List<String>>();
        var chessBoard = new char[n][n];
        for (char[] chars : chessBoard) {
            Arrays.fill(chars, '.');
        }
        solveForQueens(chessBoard, 0, result);
        return result;
    }

    void solveForQueens(char[][] chessBoard, int row, List<List<String>> result) {
        if (row == chessBoard.length) {
            var positions = new ArrayList<String>();
            var sb = new StringBuilder();
            for (char[] aRow : chessBoard) {
                sb.setLength(0);
                for (char aColumn : aRow) {
                    sb.append(aColumn);
                }
                positions.add(sb.toString());
            }
            result.add(positions);
            Util.printMatrix(chessBoard, "|");
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
