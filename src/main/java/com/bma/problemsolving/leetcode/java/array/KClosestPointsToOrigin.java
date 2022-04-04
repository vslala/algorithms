package com.bma.problemsolving.leetcode.java.array;

import java.util.Arrays;

/**
 * 973. K Closest Points to Origin
 * Given an array of points where points[i] = [xi, yi] represents a point on the X-Y plane and an integer k, return the k closest points to the origin (0, 0).
 * The distance between two points on the X-Y plane is the Euclidean distance (i.e., √(x1 - x2)2 + (y1 - y2)2).
 * You may return the answer in any order. The answer is guaranteed to be unique (except for the order that it is in).
 *
 * @author varun.shrivastava
 */
class KClosestPointsToOrigin {
    private int n;

    public int[][] kClosest(int[][] points, int k) {
        if (points.length == k) {
            return points;
        }
        this.n = points.length;

        int lo = 0;
        int hi = n - 1;
        while (lo < hi) {
            int pivot = partition(points, lo, hi);
            if (pivot < k) lo = pivot + 1;
            else if (pivot > k) hi = pivot - 1;
            else return Arrays.copyOf(points, k);
        }

        return Arrays.copyOf(points, k);
    }

    private int partition(int[][] input, int lo, int hi) {
        int pivot = choosePivot(lo, hi);
        // move pivot at the end
        swap(input, pivot, hi);
        pivot = hi;

        int i = lo;
        int j = i;
        int pivotVal = squaredDist(input[pivot]);
        while (j < pivot) {
            if (squaredDist(input[j]) < pivotVal) {
                swap(input, i, j);
                i++;
            }
            j++;
        }

        // put the pivot in its correct position
        swap(input, i, pivot);
        pivot = i;

        return pivot;
    }

    private int choosePivot(int start, int end) {
        return start + ((end - start) / 2);
    }

    private int squaredDist(int[] point) {
        return point[0] * point[0] + point[1] * point[1];
    }

    private void swap(int[][] points, int i,  int j) {
        int[] tmp = points[i];
        points[i] = points[j];
        points[j] = tmp;
    }
}
