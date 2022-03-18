package com.bma.problemsolving.leetcode.java.array;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.stream.Collectors;

import static com.bma.problemsolving.Model.Interval;

/**
 * 253. Meeting Rooms II
 * Given an array of meeting time intervals intervals where intervals[i] = [starti, endi], return the minimum number of conference rooms required.
 *
 * @author varun.shrivastava
 */
class MeetingRooms2 {

    /**
     * Steps to solve:
     * 1. Convert the input array into a better representation something like Interval {@link Interval}
     * 2. Sort the Intervals using interval.start {@link Interval#start}
     * 3. Create a Min Heap on {@link Interval#end}
     * 4. Iterate over every interval:
     *      - if this is the first meeting e.g. rooms are empty:
     *          - add to the heap
     *      - if this interval is overlapping with the earliest ending meeting
     *          - increment meeting rooms count
     *          - add this interval to the heap
     *      - if not overlapping:
     *          - remove the most recent ended meeting room
     *          - add this interval to the heap
     *
     * @param input array of intervals {start, end};
     * @return number of simultaneous meeting rooms required to compensate all the meetings without waiting times
     */
    public int minMeetingRooms(int[][] input) {
        List<Interval> intervals = Arrays.stream(input).map(Interval::new).sorted(Comparator.comparingInt(interval -> interval.start)).collect(Collectors.toList());
        PriorityQueue<Interval> rooms = new PriorityQueue<>(Comparator.comparing(interval -> interval.end));
        int meetingRooms = 0;

        for (Interval interval : intervals) {
            if (rooms.isEmpty()) {
                rooms.offer(interval);
                meetingRooms++;
            } else if (rooms.peek().isOverlapping(interval)) {
                meetingRooms++;
                rooms.offer(interval);
            } else {
                rooms.poll();
                rooms.offer(interval);
            }
        }

        return meetingRooms;
    }
}
