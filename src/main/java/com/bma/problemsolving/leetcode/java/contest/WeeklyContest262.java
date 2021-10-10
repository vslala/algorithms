package com.bma.problemsolving.leetcode.java.contest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

/**
 * 2032. Two Out of Three
 * Given three integer arrays nums1, nums2, and nums3,
 * return a distinct array containing all the values that are present
 * in at least two out of the three arrays. You may return the values in any order.
 *
 * @author varun.shrivastava
 */
public class WeeklyContest262 {
    public List<Integer> twoOutOfThree(int[] nums1, int[] nums2, int[] nums3) {
        nums1 = findDistinctItems(nums1);
        nums2 = findDistinctItems(nums2);
        nums3 = findDistinctItems(nums3);

        var mem = new HashMap<Integer, Integer>();
        countFrequency(nums1, mem);
        countFrequency(nums2, mem);
        countFrequency(nums3, mem);

        var result = new ArrayList<Integer>();
        mem.forEach((key, val) -> {
            if (val > 1) {
                result.add(key);
            }
        });

        return result;
    }

    private void countFrequency(int[] nums, HashMap<Integer, Integer> mem) {
        for (int i = 0; i < nums.length; i++) {
            mem.put(nums[i], mem.getOrDefault(nums[i], 0) + 1);
        }
    }

    private int[] findDistinctItems(int[] nums) {
        var distinct = new HashSet<Integer>();
        for (int val : nums) {
            distinct.add(val);
        }
        nums = distinct.stream().mapToInt(i -> i).toArray();
        return nums;
    }

}
