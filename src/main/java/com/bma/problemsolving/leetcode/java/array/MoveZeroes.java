package com.bma.problemsolving.leetcode.java.array;

/**
 * 283. Move Zeroes
 * Given an integer array nums, move all 0's to the end of it while maintaining the relative order of the non-zero elements.
 * Note that you must do this in-place without making a copy of the array.
 *
 * @author varun.shrivastava
 */
public class MoveZeroes {
    public void moveZeroes(int[] nums) {
        var count = 0;

        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0) {
                count++;
            } else if (count > 0) {
                nums[i - count] = nums[i];
                nums[i] = 0;
            }
        }
    }
}
