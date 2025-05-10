package com.bma.problemsolving.leetcode.java.blind75;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

class MergeIntervals {
    public int[][] merge(int[][] intervals) {
        Arrays.sort(intervals, Comparator.comparingInt(o -> o[0]));
        var output = new ArrayList<int[]>();
        output.add(intervals[0]);

        for (int j = 1; j < intervals.length; j++) {
            if (isOverlapping(output.getLast(), intervals[j])) {
                int[] mergedInterval = mergeInterval(intervals[j], output.getLast());
                output.set(output.size() - 1, mergedInterval);
            } else {
                output.add(intervals[j]);
            }
        }

        return output.toArray(new int[output.size()][2]);
    }

    private int[] mergeInterval(int[] interval1, int[] interval2) {
        interval1[0] = Math.min(interval1[0], interval2[0]);
        interval1[1] = Math.max(interval1[1], interval2[1]);
        return interval1;
    }

    private boolean isOverlapping(int[] interval1, int[] interval2) {
        int interval2StartTime = interval2[0];
        int interval1EndTime = interval1[1];
        int interval1StartTime = interval1[0];

        if (interval2StartTime > interval1EndTime) return false;
        return interval2StartTime >= interval1StartTime;
    }


}
