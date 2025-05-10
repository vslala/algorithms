package com.bma.problemsolving.leetcode.java.blind75;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

class FindKSumOfAnArray {

    private final Random rand = new Random();

    public long kSum(int[] nums, int k) {
        // step 1: find all subsequence sum
        List<Long> subsequenceSum = generateAllSubsequenceSum(nums);
        int selectedIndex = findKthSum(subsequenceSum, 0, subsequenceSum.size() - 1, k - 1);

        return subsequenceSum.get(selectedIndex);
    }

    private int findKthSum(List<Long> subsequenceSum, int start, int end, int k) {
        if (start >= end) return start;

        int pivot = start + rand.nextInt(end - start + 1);
        Collections.swap(subsequenceSum, pivot, end);
        long pivotVal = subsequenceSum.get(end);

        int partitionIndex = start;
        for (int i = start; i < end; i++) {
            if (subsequenceSum.get(i) > pivotVal) {
                Collections.swap(subsequenceSum, i, partitionIndex);
                partitionIndex++;
            }
        }

        Collections.swap(subsequenceSum, partitionIndex, end);

        if (partitionIndex == k) {
            return partitionIndex;
        } else if (partitionIndex < k) {
            return findKthSum(subsequenceSum, partitionIndex + 1, end, k);
        } else {
            return findKthSum(subsequenceSum, start, partitionIndex - 1, k);
        }
    }

    private List<Long> generateAllSubsequenceSum(int[] nums) {
        List<Long> subsequenceSum = new ArrayList<>();
        int total = 1 << nums.length; // 2^n
        for (int mask = 0; mask < total; mask++) {
            long sum = 0;
            for (int i = 0; i < nums.length; i++) {
                if ((mask & (1 << i)) != 0) {
                    sum += nums[i];
                }
            }
            subsequenceSum.add(sum);
        }
        return subsequenceSum;
    }
}
