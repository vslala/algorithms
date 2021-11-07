package com.bma.problemsolving.leetcode.java.dfsbfs;

import com.bma.problemsolving.leetcode.Model;

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
        dfs(image, sr, sc, newColor);

        return image;
    }

    private void dfs(int[][] image, int sr, int sc, int newColor) {
        if (image[sr][sc] != newColor) {
            image[sr][sc] = newColor;
            if (sr >= 1) {
                dfs(image, sr - 1, sc, newColor);
            }

            if (sc >= 1) {
                dfs(image, sr, sc - 1, newColor);
            }

            if (sr < image.length - 1) {
                dfs(image, sr + 1, sc, newColor);
            }

            if (sc < image[0].length - 1) {
                dfs(image, sr, sc + 1, newColor);
            }
        }
    }

    public int[][] floodFillBfs(int[][] image, int sr, int sc, int newColor) {
        var color = image[sr][sc];
        if (color == newColor) {
            return image;
        }

        var q = new LinkedList<Model.Coordinate>();
        q.add(new Model.Coordinate(sr, sc));

        while (!q.isEmpty()) {
            var curr = q.pollFirst();
            image[curr.getRow()][curr.getCol()] = newColor;

            var top = new Model.Coordinate(curr.getRow() - 1, curr.getCol());
            var bottom = new Model.Coordinate(curr.getRow() + 1, curr.getCol());
            var right = new Model.Coordinate(curr.getRow(), curr.getCol() + 1);
            var left = new Model.Coordinate(curr.getRow(), curr.getCol() - 1);

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

    private boolean matchingColor(int[][] image, Model.Coordinate coord, int color) {
        return image[coord.getRow()][coord.getCol()] == color;
    }

    private boolean isInBounds(int[][] image, Model.Coordinate coord) {
        int r = coord.getRow();
        int c = coord.getCol();
        return r >= 0 && r < image.length && c >= 0 && c < image[r].length;
    }

}
