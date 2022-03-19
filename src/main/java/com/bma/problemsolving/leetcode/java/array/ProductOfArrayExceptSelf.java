package com.bma.problemsolving.leetcode.java.array;

/**
 * 238. Product of Array Except Self
 * Given an integer array nums, return an array answer such that answer[i] is equal to the product of all the elements of nums except nums[i].
 *
 * The product of any prefix or suffix of nums is guaranteed to fit in a 32-bit integer.
 *
 * You must write an algorithm that runs in O(n) time and without using the division operation.
 */
class ProductOfArrayExceptSelf {
    /**
     * Steps to solve the problem:
     * 1. Calculate prefix product for each index of the given input {@link #calculatePrefixProduct(int[])}
     * 2. Calculate suffix product for each index of the given input {@link #calculateSuffixProduct(int[])}}
     * 3. Multiply each index of prefixProduct and suffixProduct into answer array
     *
     * Time Complexity: O(n)
     * Space Complexity: O(n)
     *
     * @param nums array of numbers
     * @return product of array except self
     */
    public int[] productExceptSelf(int[] nums) {
        int[] prefix = calculatePrefixProduct(nums); // O(n)
        int[] suffix = calculateSuffixProduct(nums); // O(n)

        // O(n)
        int[] answer = new int[nums.length];
        for (int i = 0; i < nums.length; i++)
            answer[i] = prefix[i] * suffix[i];

        return answer;
    }

    private int[] calculateSuffixProduct(int[] nums) {
        int[] suffix = new int[nums.length];
        suffix[nums.length - 1] = 1;
        for (int i = nums.length - 2; i >= 0; i--)
            suffix[i] = suffix[i + 1] * nums[i + 1];
        return suffix;
    }

    private int[] calculatePrefixProduct(int[] nums) {
        int[] prefix = new int[nums.length];
        prefix[0] = 1;
        for (int i = 1; i < nums.length; i++)
            prefix[i] = prefix[i - 1] * nums[i - 1];
        return prefix;
    }

    /**
     Steps to solve the problem:
     * 1. Calculate prefix product using the answer array for each index of the given input
     * 2. Calculate suffix product using the answer array as prefix for each index of the given input
     * 3. Multiply each index of answer[i] and suffix into answer array
     *
     * Time Complexity: O(n)
     * Space Complexity: O(1) [excluding the output answer array]
     *
     * @param nums array of numbers
     * @return product of array except self
     */
    public int[] productExceptSelfWithoutUsingExtraSpace(int[] nums) {
        int[] answer = new int[nums.length];

        answer[0] = 1;
        for (int i = 1; i < nums.length; i++) {
            answer[i] = answer[i - 1] * nums[i - 1];
        }

        int suffix = 1;
        for (int i = nums.length - 1; i >= 0; i--) {
            answer[i] = answer[i] * suffix;
            suffix = nums[i] * suffix;
        }

        return answer;
    }

}
