package com.bma.problemsolving;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Value;
import lombok.With;

public class Model {

    public record Coordinate(int row, int col) {
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

        public Coordinate top() {
            return this.minusRow(1);
        }

        public Coordinate bottom() {
            return this.plusRow(1);
        }

        public Coordinate left() {
            return this.minusCol(1);
        }

        public Coordinate right() {
            return this.plusCol(1);
        }

        public boolean isInBounds(int[][] grid) {
            return row >= 0 && row < grid.length &&
                    col >= 0 && col < grid[row].length;
        }

        public boolean isInBounds(char[][] grid) {
            return row >= 0 && row < grid.length &&
                    col >= 0 && col < grid[row].length;
        }

        public Coordinate plus(Coordinate coordinate) {
            return new Coordinate(row + coordinate.row(), col + coordinate.col());
        }

        public int value(int[][] grid) {
            return grid[this.row][this.col];
        }

        public char value(char[][] grid) {
            return grid[this.row][this.col];
        }
    }

    @With
    @Value
    public static class Interval {
        public int start;
        public int end;

        public Interval(int[] interval) {
            this.start = interval[0];
            this.end = interval[1];
        }

        public Interval(int start, int end) {
            this.start = start;
            this.end = end;
        }

        public Interval merge(Interval interval) {
            return this.withStart(start).withEnd(Math.max(end, interval.end));
        }

        public int[] toArray() {
            return new int[]{start, end};
        }

        public boolean isOverlapping(Interval interval) {
            return this.end > interval.start;
        }
    }

    @Data
    @AllArgsConstructor
    public static class Node {
        private int val;
        private Node left;
        private Node right;
        private Node next;
    }
}