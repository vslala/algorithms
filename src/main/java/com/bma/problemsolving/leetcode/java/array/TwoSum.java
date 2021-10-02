package com.bma.problemsolving.leetcode.java.array;

import java.util.HashMap;

/**
 * 1. Two Sum
 * Given an array of integers nums and an integer target, return indices of the two numbers such that they add up to target.
 * You may assume that each input would have exactly one solution, and you may not use the same element twice.
 * You can return the answer in any order.
 * Question ref -> https://leetcode.com/problems/two-sum/
 *
 * @author varun.shrivastava
 */
public class TwoSum {

    public int[] solution(int[] nums, int target) {
        var inverseIndexMap = new HashMap<Integer,Integer>();
        int length = nums.length;

        for (int index = 0; index < length; index++) {
            int val = nums[index];
            int inverseVal = target - val;

            if (inverseIndexMap.containsKey(val)) {
                if (nums[inverseIndexMap.get(val)] + val == target) {
                    return new int[] {index, inverseIndexMap.get(val)};
                }
            } else {
                inverseIndexMap.put(inverseVal, index);
            }

        }
        return new int[]{};
    }

    public static void main(String[] args) {
        var nums = new int[]{2, 7, 11, 15};
        int target = 9;
        var twoSum = new TwoSum();
        int[] output = twoSum.solution(nums, target);

        for (int i : output) {
            System.out.println(i);
        }
    }
}
