package com.bma.problemsolving.leetcode.java.blind75;

import java.util.ArrayList;
import java.util.List;

class InsertInterval {
    public int[][] insert(int[][] intervals, int[] newInterval) {
        if (intervals.length == 0) {
            return new int[][]{newInterval};
        }
        var ls = insertNewInterval(intervals, newInterval);

        List<int[]> result = mergeOverlappingIntervals(ls);

        return result.toArray(new int[result.size()][2]);


    }

    private List<int[]> mergeOverlappingIntervals(ArrayList<int[]> ls) {
        List<int[]> result = new ArrayList<>();
        result.add(ls.getFirst());
        for (int i = 1; i < ls.size(); i++) {
            int[] curr = ls.get(i);
            if (isOverlapping(result.getLast(), curr)) {
                int[] merged = merge(result.getLast(), curr);
                result.set(result.size() - 1, merged);
            } else {
                result.add(curr);
            }
        }
        return result;
    }

    private static ArrayList<int[]> insertNewInterval(int[][] intervals, int[] newInterval) {
        boolean newIntervalAdded = false;
        var ls = new ArrayList<int[]>();
        for (int[] curr : intervals) {
            if (!newIntervalAdded && newInterval[0] < curr[0]) {
                ls.add(newInterval);
                ls.add(curr);
                newIntervalAdded = true;
            } else {
                ls.add(curr);
            }
        }

        if (!newIntervalAdded) {
            ls.add(newInterval);
        }
        return ls;
    }

    private int[] merge(int[] first, int[] second) {
        int[] merged = new int[2];
        merged[0] = Math.min(first[0], second[0]);
        merged[1] = Math.max(first[1], second[1]);

        return merged;
    }

    private boolean isOverlapping(int[] first, int[] second) {
        if (first[1] >= second[0]) return true;
        return false;
    }
}
