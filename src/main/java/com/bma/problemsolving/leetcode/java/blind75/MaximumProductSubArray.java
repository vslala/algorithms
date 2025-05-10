package com.bma.problemsolving.leetcode.java.blind75;

import static java.lang.Math.*;

public class MaximumProductSubArray {

    /**
     * Solution is O(n) with Dynamic Programming.
     * We keep track of max and min till the index.
     * We keep track of min because that could turn out to be the max product if it encounters any negative number again.
     * Therefore, keeping track of min, we handle that case.
     *
     * @param nums
     * @return
     */
    public int maxProduct(int[] nums) {
        int maxSoFar = nums[0];
        int minSoFar = nums[0];
        int result = maxSoFar;

        for (int i = 1; i < nums.length; i++) {
            int curr = nums[i];
            int temp = max(curr, max(curr * maxSoFar, curr * minSoFar));
            minSoFar = min(curr, min(curr * minSoFar, curr * maxSoFar));
            maxSoFar = temp;

            result = max(result, maxSoFar);
        }

        return result;
    }
}
