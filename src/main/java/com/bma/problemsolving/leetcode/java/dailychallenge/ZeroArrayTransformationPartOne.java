package com.bma.problemsolving.leetcode.java.dailychallenge;

/**
 * @author Varun Shrivastava
 * @github www.github.com/vslala
 * @date 20/05/2025
 */
class ZeroArrayTransformationPartOne {
    public boolean isZeroArray(int[] nums, int[][] queries) {
        int n = nums.length;
        int[] delta = new int[n + 1];
        for (int[] query : queries) {
            int start = query[0];
            int end = query[1];
            delta[start] += 1;
            delta[end + 1] -= 1;
        }

        int[] ops = new int[n];
        int operations = 0;
        for (int i = 0; i < n; i++) {
            operations += delta[i];
            ops[i] = operations;
        }

        for (int i = 0; i < n; i++) {
            if (ops[i] < nums[i]) {
                return false;
            }
        }

        return true;
    }
}
