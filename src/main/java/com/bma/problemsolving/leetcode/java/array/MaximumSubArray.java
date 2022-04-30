package com.bma.problemsolving.leetcode.java.array;

import java.util.Arrays;

/**
 * 53. Maximum Subarray
 * Given an integer array nums, find the contiguous subarray (containing at least one number) which has the largest sum and return its sum.
 * A subarray is a contiguous part of an array.
 *
 * This is a typical question to practice Kadane's algorithm
 *
 * @author varun.shrivastava
 */
class MaximumSubArray {

    public int maxSubArraySum(int[] nums) {
        int result = nums[0];
        int sum = nums[0];

        for (int i = 1; i < nums.length; i++) {
            int num = nums[i];
            sum = Math.max(num, sum + num);
            result = Math.max(sum, result);
        }

        return result;
    }

    public int[] maxSubArray(int[] nums) {
        int bestStart = 0;
        int bestEnd = bestStart;

        int currentSum = 0;
        int bestSum = nums[0];

        int currentStart = 0;
        for (int currentEnd = 0; currentEnd < nums.length; currentEnd++) {
            int x = nums[currentEnd];

            if (currentSum <= 0) {
                // start a new sequence whenever current sum goes below 0
                currentStart = currentEnd;
                currentSum = x;
            } else {
                // extend existing solution with current element
                currentSum += x;
            }

            if (currentSum > bestSum) {
                bestSum = currentSum;
                bestStart = currentStart;
                bestEnd = currentEnd + 1; // +1 to make 'bestEnd' exclusive
            }
        }

        System.out.println("Best Start = " + bestStart + ", Best End: " + bestEnd);
        if (bestStart == bestEnd) {
            // the sequence is strictly decreasing
            return new int[]{nums[0]};
        }

        int lengthOfMaxSubArray = bestEnd - bestStart;
        return Arrays.copyOfRange(nums, bestStart, bestStart + lengthOfMaxSubArray);
    }
}
