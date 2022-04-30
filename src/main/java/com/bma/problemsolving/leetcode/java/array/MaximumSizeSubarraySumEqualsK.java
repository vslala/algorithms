package com.bma.problemsolving.leetcode.java.array;

import com.bma.algorithms.sort.elementary.Util;

import java.util.HashMap;
import java.util.Map;

/**
 * 325. Maximum Size Subarray Sum Equals k
 * Given an integer array nums and an integer k, return the maximum length of a subarray that sums to k. If there is not one, return 0 instead.
 *
 * @author varun.shrivastava
 */
class MaximumSizeSubarraySumEqualsK {

    /**
     * The rationale behind the following solution is as followS:
     * if there exists some subarray from i to j summing to  k in nums, then we know that
     * -> prefixSum[j] - prefixSum[i] = k
     * -> prefixSum[j] - prefixSum[i] - k = 0
     * -> prefixSum[j] - k = prefixSum[i]
     *
     * Essentially, we are storing running sum in the hashmap with its associated index.
     * For ex: [1,-1,5,-2,3], k = 3
     * prefixSum	| index	| prefixSum - k	| best	|
     * 1			| 0		| 1 - 3 = -2		| 0
     * 0			| 1		| 0 - 3 = -3		| 0
     * 5			| 2		| 5 - 3 = 2		    | 0
     * 3			| 3		| 3 - 3 = 0		    | 4 => running sum equals 'k', this must be the max so far starting from index 0
     * 6			| 4		| 6 - 3 = 3		    | 4
     *
     * @param nums arr of integers
     * @param k subarray sum to find
     * @return size of the subarray whose sum equals to 'k'
     */
    public int maxSubArrayLen(int[] nums, int k) {
        Map<Integer, Integer> prefixIndices = new HashMap<>();
        StringBuilder sb = new StringBuilder();
        sb.append("prefixSum").append("\t| ")
                .append("index").append("\t| ")
                .append("prefixSum - k").append("\t| ")
                .append("best").append("\t| ")
                .append(System.lineSeparator());

        int best = 0;
        int runningSum = 0;
        for (int i = 0; i < nums.length; i++) {
            runningSum += nums[i];
            int sumToFind = runningSum - k;
            if (runningSum == k)
                best = i + 1;

            if (prefixIndices.containsKey(sumToFind))
                best = Math.max(best, i - prefixIndices.get(sumToFind));

            prefixIndices.putIfAbsent(runningSum, i);

            sb.append(runningSum).append("\t\t\t| ")
                    .append(i).append("\t\t| ")
                    .append(runningSum + " - " + k + " = ").append(sumToFind).append("\t\t| ")
                    .append(best)
                    .append(System.lineSeparator());
        }

        Util.println(sb);
        return best;
    }
}
