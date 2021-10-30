package com.bma.problemsolving.leetcode.java.array;

import java.util.ArrayList;

/**
 * 977. Squares of a Sorted Array
 * Given an integer array nums sorted in non-decreasing order, return an array of the squares of each number sorted in non-decreasing order.
 *
 * @author varun.shrivastava
 */
public class SquareOfASortedArray {

    public int[] sortedSquares(int[] nums) {
        var result = new int[nums.length];
        var positives = new ArrayList<Integer>();
        var negatives = new ArrayList<Integer>();

        // Divide into positive and negatives
        for (int num : nums) {
            if (num >= 0) {
                positives.add(num);
            } else {
                negatives.add(Math.abs(num));
            }
        }

        // Merge both arrays while squaring each element
        var index = 0;
        var posIndex = 0;
        var negIndex = negatives.size() - 1;
        while (posIndex < positives.size() && negIndex >= 0) {
            if (positives.get(posIndex) < negatives.get(negIndex)) {
                result[index++] = positives.get(posIndex) * positives.get(posIndex);
                posIndex++;
            } else {
                result[index++] = negatives.get(negIndex) * negatives.get(negIndex);
                negIndex--;
            }
        }

        while (posIndex < positives.size()) {
            result[index++] = positives.get(posIndex) * positives.get(posIndex);
            posIndex++;
        }

        while (negIndex >= 0) {
            result[index++] = negatives.get(negIndex) * negatives.get(negIndex);
            negIndex--;
        }

        return result;
    }
}
