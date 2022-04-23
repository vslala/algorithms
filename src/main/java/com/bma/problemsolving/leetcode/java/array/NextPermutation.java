package com.bma.problemsolving.leetcode.java.array;

import static com.bma.algorithms.sort.elementary.Util.reverse;
import static com.bma.algorithms.sort.elementary.Util.swap;

/**
 * 31. Next Permutation
 * A permutation of an array of integers is an arrangement of its members into a sequence or linear order.
 *
 * For example, for arr = [1,2,3], the following are considered permutations of arr: [1,2,3], [1,3,2], [3,1,2], [2,3,1].
 * The next permutation of an array of integers is the next lexicographically greater permutation of its integer. More formally, if all the permutations of the array are sorted in one container according to their lexicographical order, then the next permutation of that array is the permutation that follows it in the sorted container. If such arrangement is not possible, the array must be rearranged as the lowest possible order (i.e., sorted in ascending order).
 *
 * For example, the next permutation of arr = [1,2,3] is [1,3,2].
 * Similarly, the next permutation of arr = [2,3,1] is [3,1,2].
 * While the next permutation of arr = [3,2,1] is [1,2,3] because [3,2,1] does not have a lexicographical larger rearrangement.
 * Given an array of integers nums, find the next permutation of nums.
 *
 * The replacement must be in place and use only constant extra memory.
 *
 * @author varun.shrivastava
 */
class NextPermutation {
    /**
     * Steps to find next lexicographic permutation
     * 1. Find the first dip in the array from right {@link #findDipIndex(int[])}
     *  - [1,5,8,4,7,6,5,3,1]: Here the first dip from the right is 4 that is adjacent to 7, therefore, 4 < 7 (dip)
     * 2. Now we need to find the next big number than 4 (dip) from the right and swap it {@link #findNextGreaterNumberIndex(int[], int)}
     *  - [1,5,8,4,7,6,5,3,1] => [1,5,8,5,7,6,4,3,1]: here, 5 is the next greater number from the right therefore, we swap 4 with 5
     * 3. Reverse the numbers from the initial dip index to the end (Util.reverse)
     *  - Ex: [1,5,8,4,7,6,5,3,1] -> [1,5,8,5,7,6,4,3,1] -> [1,5,8,5,1,3,4,6,7]
     *
     * @param nums
     */
    public void nextPermutation(int[] nums) {
        int n = nums.length;
        int dipIndex = findDipIndex(nums);
        if (dipIndex == -1) {
            reverse(nums, 0, n - 1);
            return;
        }

        int nextGreaterNumber = findNextGreaterNumberIndex(nums, nums[dipIndex]);
        swap(nums, dipIndex, nextGreaterNumber);
        reverse(nums, dipIndex + 1, n - 1);
    }

    private int findNextGreaterNumberIndex(int[] nums, int num) {
        int n = nums.length;
        while (--n > 0)
            if (nums[n] > num) return n;

        return -1;
    }

    private int findDipIndex(int[] nums) {
        int i = nums.length - 2;
        while (i >= 0 && nums[i] >= nums[i + 1]) i--;

        return i;
    }
}
