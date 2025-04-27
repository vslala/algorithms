package com.bma.problemsolving.leetcode.java.citadel;

import java.util.HashSet;

public class LongestConsecutiveSequence {

    public int longestConsecutive(int[] nums) {
        int longestSequence = 0;
        var set = new HashSet<Integer>();

        for (int num: nums) {
            set.add(num);
        }

        for (int num: set) {
            if (!set.contains(num - 1)) {
                int sequence = 0;
                int curr = num;
                while (set.contains(curr)) {
                    sequence++;
                    curr++;
                }

                longestSequence = Math.max(sequence, longestSequence);
            }
        }

        return longestSequence;
    }
}
