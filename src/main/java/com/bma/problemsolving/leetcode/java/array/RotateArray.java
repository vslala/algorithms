package com.bma.problemsolving.leetcode.java.array;

/**
 * 189. Rotate Array
 * Given an array, rotate the array to the right by k steps, where k is non-negative.
 *
 * @author varun.shrivastava
 */
public class RotateArray {

    public void rotate(int[] nums, int k) {
        var n = nums.length;
        var steps = k % n;

        var rightArr = new int[steps];
        for (int i = n - steps, ri = 0; i < n; i++, ri++) {
            rightArr[ri] = nums[i];
        }

        // shift the left array to the right by given steps
        System.arraycopy(nums, 0, nums, steps, n - steps);

        // copy right array to the start
        System.arraycopy(rightArr, 0, nums, 0, rightArr.length);

    }
}
