package com.bma.problemsolving.leetcode.java.citadel;

import java.util.HashMap;

public class TwoSum {

    public int[] twoSum(int[] nums, int target) {
        var dict = new HashMap<Integer, Integer>();
        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];
            if (dict.containsKey(target - num)) {
                return new int[]{i, dict.get(target - num)};
            }

            dict.put(num, i);

        }

        return new int[] {};
    }

}
