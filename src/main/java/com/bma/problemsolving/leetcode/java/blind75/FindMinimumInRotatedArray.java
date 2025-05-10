package com.bma.problemsolving.leetcode.java.blind75;

import com.bma.algorithms.sort.elementary.Util;

import java.util.Map;

class FindMinimumInRotatedArray {

    public int findMin(int[] nums) {
        int mid;
        int low = 0;
        int high = nums.length - 1;
        Util.println(nums);
        Util.format("{low:5}{high:5}{mid:5}{mid_value:12}{low_value:12}{high_value:12}{discarding_left:18}", Map.of(
                "low", "low",
                "high", "high",
                "mid", "mid",
                "mid_value", "mid_value",
                "low_value", "low_value",
                "high_value", "high_value",
                "discarding_left", "discarding_left"
        ));
        while (low < high) {
            mid = low + ((high - low) / 2);
            Util.format(
                    "{low:5}{high:5}{mid:5}{mid_value:12}{low_value:12}{high_value:12}{discarding_left:18}",
                    Map.of(
                            "low", low,
                            "high", high,
                            "mid", mid,
                            "mid_value", nums[mid],
                            "low_value", nums[low],
                            "high_value", nums[high],
                            "discarding_left", nums[mid] > nums[high]
                    )
            );
            Util.println("-----------------------------------------------------------------------------");
            if (nums[mid] > nums[high]) {
                low = mid + 1;
            } else {
                high = mid;
            }
        }

        return nums[high];
    }
}
