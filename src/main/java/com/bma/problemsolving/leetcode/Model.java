package com.bma.problemsolving.leetcode;

import lombok.AllArgsConstructor;
import lombok.Data;

public class Model {

    @Data
    @AllArgsConstructor
    public static class Coordinate {
        int row;
        int col;

        public Coordinate minusRow(int number) {
            return new Coordinate(row - number, col);
        }

        public Coordinate minusCol(int number) {
            return new Coordinate(row, col - number);
        }

        public Coordinate plusRow(int number) {
            return new Coordinate(row + number, col);
        }

        public Coordinate plusCol(int number) {
            return new Coordinate(row, col + number);
        }

        public boolean isInBounds(int[][] grid) {
            return row >= 0 && row < grid.length &&
                    col >= 0 && col < grid[row].length;
        }
    }
}