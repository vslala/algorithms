package com.bma.algorithms.leetcode;

import java.util.HashMap;

// Question ref -> https://leetcode.com/problems/two-sum/
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
