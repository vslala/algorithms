package com.bma.problemsolving.leetcode.java.blind75;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * @author Varun Shrivastava
 * @github www.github.com/vslala
 * @date 18/05/2025
 */
class MeetingRoomPart2 {
    public int minMeetingRooms(int[][] intervals) {
        int[] start = new int[intervals.length];
        int[] end = new int[intervals.length];

        for (int i = 0; i < intervals.length; i++) {
            int x = intervals[i][0];
            int y = intervals[i][1];

            start[i] = x;
            end[i] = y;
        }

        Arrays.sort(start);
        Arrays.sort(end);

        int startPointer = 0;
        int endPointer = 0;
        int room = 0;
        while (startPointer < intervals.length) {
            if (start[startPointer] >= end[endPointer]) {
                room -= 1;
                endPointer++;
            }

            room += 1;
            startPointer += 1;
        }

        return room;
    }

    public int minMeetingRoomsApproach2(int[][] intervals) {
        var minHeap = new PriorityQueue<Integer>();
        Arrays.sort(intervals, Comparator.comparingInt(o -> o[0]));
        minHeap.offer(intervals[0][1]);

        for (int i = 1; i < intervals.length; i++) {
            assert !minHeap.isEmpty();
            if (intervals[i][0] > minHeap.peek()) {
                minHeap.poll();
            }

            minHeap.offer(intervals[i][1]);
        }

        return minHeap.size();
    }
}
