package com.bma.problemsolving.leetcode.java.array;

import java.util.HashMap;
import java.util.Map;

/**
 * 523. Continuous Subarray Sum
 * Given an integer array nums and an integer k, return true if nums has a continuous subarray of size at least two whose elements sum up to a multiple of k, or false otherwise.
 * An integer x is a multiple of k if there exists an integer n such that x = n * k. 0 is always a multiple of k.
 *
 * @author varun.shrivastava
 */
class ContinuousSubarraySum {

    public boolean checkSubarraySum(int[] nums, int k) {
        Map<Integer, Integer> remainderIndexMap = new HashMap<>();
        // this is to handle the base case arr = [23,2,4,6,6], k=7
        remainderIndexMap.put(0, -1);
        int runningSum = 0;
        for (int i = 0; i < nums.length; i++) {
            int val = nums[i];
            runningSum += val;
            int remainder = runningSum % k;
            if (remainderIndexMap.containsKey(remainder)) {
                int pos = remainderIndexMap.get(remainder);
                // this condition is needed because there should be at-least 2 numbers in the continuous sub-array
                if (i - pos > 1) return true;
            } else {
                remainderIndexMap.put(remainder, i);
            }
        }

        return false;
    }
}
