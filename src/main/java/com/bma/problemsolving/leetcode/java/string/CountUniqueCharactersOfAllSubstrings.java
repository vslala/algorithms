package com.bma.problemsolving.leetcode.java.string;

import java.util.Arrays;

class CountUniqueCharactersOfAllSubstrings {
    public int uniqueLetterString(String s) {
        int[][] index = new int[26][2];
        for (int[] idx : index) {
            Arrays.fill(idx, -1);  // A -> [-1, -1]
        }

        int result = 0;
        int size = s.length();
        int mod = (int) Math.pow(10, 9) + 7;

        // this code calculates the curr and prev index for the character
        // ex - ABA => 'A' => index[0] = [0, 2];
        for (int i = 0; i < size; i++) {
            int c = s.charAt(i) - 'A';
            int distanceBetweenSecondAndFirstOccurrence = index[c][1] - index[c][0];    // prev - secondPrev
            int distanceBetweenCurrentAndSecondOccurrence = i - index[c][1];            // curr - prev
            result = (result + (distanceBetweenCurrentAndSecondOccurrence * distanceBetweenSecondAndFirstOccurrence) % mod) % mod;

            index[c] = new int[] {index[c][1], i};
        }

        // This code calculates the contribution of each character uniquely in the number of substrings
        // ABC = A, AB, ABC
        // ABC = AB, B, BC, ABC
        // ABC = A, B, C, AB, BC, AC ABC
        // Unique Substrings = A, AB, ABC, B, BC, C => Count of unique chars = 1 + 2 + 3 + 1 + 2 + 1 = 10
        for (int c = 0; c < 26; c++) {
            int distanceBetweenSecondAndFirstOccurrence = index[c][1] - index[c][0];    // prev - secondPrev
            int distanceBetweenCurrentAndSecondOccurrence = size - index[c][1];
            result = (result + (distanceBetweenCurrentAndSecondOccurrence * distanceBetweenSecondAndFirstOccurrence) % mod) % mod;
        }

        return result;
    }
}
