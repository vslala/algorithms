package com.bma.problemsolving.leetcode.java.dfsbfs;

import lombok.AllArgsConstructor;

import java.util.LinkedList;

/**
 * 733. Flood Fill
 * ------------------
 * An image is represented by an m x n integer grid image where image[i][j] represents the pixel value of the image.
 *
 * You are also given three integers sr, sc, and newColor. You should perform a flood fill on the image starting from the pixel image[sr][sc].
 *
 * To perform a flood fill, consider the starting pixel, plus any pixels connected 4-directionally to the starting pixel of the same color as the starting pixel,
 * plus any pixels connected 4-directionally to those pixels (also with the same color), and so on.
 * Replace the color of all of the aforementioned pixels with newColor.
 *
 * Return the modified image after performing the flood fill.
 *
 * @author varun.shrivastava
 */
public class FloodFill {
    public int[][] floodFillDfs(int[][] image, int sr, int sc, int newColor) {
        int color = image[sr][sc];
        dfs(image, sr, sc, color, newColor);

        return image;
    }

    private void dfs(int[][] image, int sr, int sc, int color, int newColor) {
        if (image[sr][sc] != newColor) {
            image[sr][sc] = newColor;
            if (sr >= 1) {
                dfs(image, sr - 1, sc, color, newColor);
            }

            if (sc >= 1) {
                dfs(image, sr, sc - 1, color, newColor);
            }

            if (sr < image.length - 1) {
                dfs(image, sr + 1, sc, color, newColor);
            }

            if (sc < image[0].length - 1) {
                dfs(image, sr, sc + 1, color, newColor);
            }
        }
    }

    public int[][] floodFillBfs(int[][] image, int sr, int sc, int newColor) {
        var color = image[sr][sc];
        if (color == newColor) {
            return image;
        }

        var q = new LinkedList<Coordinate>();
        q.add(new Coordinate(sr, sc));

        while (!q.isEmpty()) {
            var curr = q.pollFirst();
            image[curr.r][curr.c] = newColor;

            var top = new Coordinate(curr.r - 1, curr.c);
            var bottom = new Coordinate(curr.r + 1, curr.c);
            var right = new Coordinate(curr.r, curr.c + 1);
            var left = new Coordinate(curr.r, curr.c - 1);

            if (isInBounds(image, top) && matchingColor(image, top, color)) {
                q.add(top);
            }

            if (isInBounds(image, bottom) && matchingColor(image, bottom, color)) {
                q.add(bottom);
            }

            if (isInBounds(image, right) && matchingColor(image, right, color)) {
                q.add(right);
            }

            if (isInBounds(image, left) && matchingColor(image, left, color)) {
                q.add(left);
            }
        }

        return image;
    }

    private boolean matchingColor(int[][] image, Coordinate coord, int color) {
        return image[coord.r][coord.c] == color;
    }

    private boolean isInBounds(int[][] image, Coordinate coord) {
        int r = coord.r;
        int c = coord.c;
        return r >= 0 && r < image.length && c >= 0 && c < image[r].length;
    }

    @AllArgsConstructor
    static class Coordinate {
        int r;
        int c;
    }
}
