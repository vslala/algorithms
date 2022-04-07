package com.bma.problemsolving.leetcode.java.array;

/**
 * 670. Maximum Swap
 * You are given an integer num. You can swap two digits at most once to get the maximum valued number.
 * Return the maximum valued number you can get.
 *
 * @author varun.shrivastava
 */
class MaximumSwap {
    /**
     * The crux is to find the **first smallest digit** from left that is smaller than the **first largest number** from right
     * and swap it. But this **first smallest number** should be smaller than the **largest number** in that index.
     *
     * Steps to solve:
     * 1. calculate smallest for every index from left {@link #smallestFromLeftToRight(char[])}
     * 2. calculate largest for every index from right {@link #maximumFromRightToLeft(char[])}
     * 3. start scanning smallest from left to right
     *  - and as soon as you find the first index that is smaller than the index of maximum
     *  - search the digit index in the array
     *  - swap index with the max index from the digits
     *
     * @param num input number
     * @return maximum number formed by swapping two digits
     */
    public int maximumSwap(int num) {
        char[] digits = String.valueOf(num).toCharArray();
        int[] smallestFromLeftToRight = smallestFromLeftToRight(digits);
        int[] maximumFromRightToLeft  = maximumFromRightToLeft(digits);

        for (int index = 0; index < digits.length; index++) {
            if (smallestFromLeftToRight[index] < maximumFromRightToLeft[index]) {
                int digitToSearch = maximumFromRightToLeft[index];
                int maxIndexFromRight = searchFirstMatchIndexFromRight(digits, (char) (digitToSearch + '0'));
                swap(digits, index, maxIndexFromRight);

                break;
            }
        }

        return Integer.parseInt(new String(digits));
    }

    private void swap(char[] digits, int index, int otherIndex) {
        char temp = digits[index];
        digits[index] = digits[otherIndex];
        digits[otherIndex] = temp;
    }

    private int searchFirstMatchIndexFromRight(char[] digits, char toSearch) {
        int index = digits.length;
        while (--index >= 0) if (digits[index] == toSearch) return index;

        return -1;
    }

    private int[] maximumFromRightToLeft(char[] digits) {
        int size = digits.length;
        // init
        int[] output = new int[size];
        int index = size - 1;
        output[index] = digits[index] - '0';

        // compute
        while (--index >= 0) output[index] = Math.max(output[index + 1], digits[index] - '0');

        return output;
    }

    private int[] smallestFromLeftToRight(char[] digits) {
        int size = digits.length;
        // init
        int[] output = new int[size];
        int index = 0;
        output[index] = digits[index] - '0';

        // compute
        while (++index < size) output[index] = Math.min(output[index - 1], digits[index] - '0');

        return output;
    }
}
