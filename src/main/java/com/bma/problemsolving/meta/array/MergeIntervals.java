package com.bma.problemsolving.meta.array;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.stream.Collectors;

/**
 * 56. Merge Intervals
 * Given an array of intervals where intervals[i] = [starti, endi],
 * merge all overlapping intervals, and return an array of the non-overlapping intervals that cover all the intervals in the input.
 *
 * @author varun.shrivastava
 */
class MergeIntervals {
    /**
     * Steps to solve:
     * 1. Convert the input array into a more expressive data structure like {@link Interval}
     * 2. Sort the intervals list in ascending order based on their start time
     * 3. Iterate over each interval and follow:
     *      - if heap is empty:
     *          - add it to the heap
     *      - if intervals are overlapping:
     *          - merge intervals
     *      - else
     *          - add to the heap
     *
     * Note: heap is a 'Max Heap' on the basis of Interval end time {@link Interval#end}
     * @param input
     * @return
     */
    public int[][] merge(int[][] input) {
        PriorityQueue<Interval> heap = new PriorityQueue<>((o1, o2) -> o2.end - o1.end);
        List<Interval> intervals = Arrays.stream(input).map(Interval::new).sorted(Comparator.comparingInt(interval -> interval.start)).collect(Collectors.toList());

        for (Interval interval: intervals) {
            if (heap.isEmpty() || !isOverlapping(heap.peek(), interval)) heap.offer(interval);
            else heap.offer(heap.poll().merge(interval));
        }

        int[][] output = new int[heap.size()][2];
        int i = 0;
        while (!heap.isEmpty()) output[i++] = heap.poll().toArray();

        return output;
    }


    private boolean isOverlapping(Interval interval1, Interval interval2) {
        return interval1.end >= interval2.start;
    }

    private class Interval {
        private int start;
        private int end;

        public Interval(int[] interval) {
            this.start = interval[0];
            this.end = interval[1];
        }

        public Interval(int start, int end) {
            this.start = start;
            this.end = end;
        }

        public Interval merge(Interval interval) {
            return new Interval(start, Math.max(end, interval.end));
        }

        public int[] toArray() {
            return new int[]{start, end};
        }
    }
}
