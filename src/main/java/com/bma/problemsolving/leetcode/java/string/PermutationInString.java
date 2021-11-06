package com.bma.problemsolving.leetcode.java.string;

import java.util.HashMap;

/**
 * 567. Permutation in String
 * Given two strings s1 and s2, return true if s2 contains a permutation of s1, or false otherwise.
 * In other words, return true if one of s1's permutations is the substring of s2.
 *
 * @author varun.shrivastava
 */
public class PermutationInString {

    public boolean checkInclusion(String s1, String s2) {
        var mem1 = new HashMap<Character, Integer>();
        var mem2 = new HashMap<Character, Integer>();

        for (char c : s1.toCharArray()) {
            mem1.put(c, mem1.getOrDefault(c, 0) + 1);
        }

        var windowSize = s1.length();
        for (int i = 0, j = i + windowSize; i <= s2.length() - windowSize; i++, j = i + windowSize) {
            mem2.clear();
            for (int k = i; k < j; k++) {
                char c = s2.charAt(k);
                mem2.put(c, mem2.getOrDefault(c, 0) + 1);
            }

            if (mem1.equals(mem2)) {
                return true;
            }
        }

        return false;
    }

    public boolean checkInclusionWithArrayAsHashMap(String s1, String s2) {
        var mem1 = new int[26];
        var mem2 = new int[26];

        for (char c : s1.toCharArray()) {
            mem1[c - 'a']++;
        }

        var windowSize = s1.length();
        for (int i = 0, j = i + windowSize; i <= s2.length() - windowSize; i++, j = i + windowSize) {
            countCharFrequencyForTheGivenWindow(s2, mem2, i, j);
            if (windowMatchesS1(s2, mem1, mem2, i, j)) {
                return true;
            }
            resetFrequencyForTheGivenWindow(s2, mem2, i, j);
        }

        return false;
    }

    private boolean windowMatchesS1(String s2, int[] mem1, int[] mem2, int i, int j) {
        boolean match = true;
        for (int k = i; k < j; k++) {
            char c = s2.charAt(k);
            if (mem1[c - 'a'] != mem2[c - 'a']) {
                match = false;
                break;
            }
        }
        return match;
    }

    private void resetFrequencyForTheGivenWindow(String s2, int[] mem2, int i, int j) {
        for (int k = i; k < j; k++) {
            char c = s2.charAt(k);
            mem2[c - 'a'] = 0;
        }
    }

    private void countCharFrequencyForTheGivenWindow(String s2, int[] mem2, int i, int j) {
        for (int k = i; k < j; k++) {
            char c = s2.charAt(k);
            mem2[c - 'a']++;
        }
    }
}
