package com.bma.problemsolving.leetcode.java.blind75;

import java.util.Arrays;
import java.util.Comparator;

/**
 * @author Varun Shrivastava
 * @github www.github.com/vslala
 * @date 18/05/2025
 */
class MeetingRooms {
    public boolean canAttendMeetings(int[][] intervals) {
        Arrays.sort(intervals, Comparator.comparing(o -> o[0]));
        for (int i = 1; i < intervals.length; i++) {
            if (intervals[i][0] < intervals[i - 1][1]) {
                return false;
            }
        }
        return true;
    }
}
