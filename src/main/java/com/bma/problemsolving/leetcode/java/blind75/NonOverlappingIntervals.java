package com.bma.problemsolving.leetcode.java.blind75;

import java.util.Arrays;
import java.util.Comparator;

class NonOverlappingIntervals {

    /**
     * Algorithm counts how many intervals it has to discard (e.g. overlapping intervals)
     * By sorting on end-times and only “keeping” an interval when its `start ≥ the last kept end (k)`,
     * every time we hit an interval with start < k we know it must overlap something we already kept, so we increment ans.
     * Thus, `ans` accumulates exactly the number of intervals we throw away (the minimum needed to get a non-overlapping set).
     *
     * In Other Words,
     * The algorithm maximizes the size of the non-overlapping set it keeps, by the classic “earliest-finish-time” greedy.
     *
     * @param intervals
     * @return
     */
    public int eraseOverlapIntervals(int[][] intervals) {
        Arrays.sort(intervals, Comparator.comparingInt(interval -> interval[1]));
        int ans = 0;
        int k = Integer.MIN_VALUE;

        for (int i = 0; i < intervals.length; i++) {
            int x = intervals[i][0];
            int y = intervals[i][1];

            if (x >= k) {
                k = y;
            } else {
                ans++;
            }
        }

        return ans;
    }

    private int[] mergeIntervals(int[] i1, int[] i2) {
        int[] merged = new int[2];
        merged[0] = Math.min(i1[0], i2[0]);
        merged[1] = Math.max(i1[1], i2[1]);

        return merged;
    }

    private boolean isOverlapping(int[] i1, int[] i2) {
        return i2[0] < i1[1];
    }
}
