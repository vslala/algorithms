package com.bma.problemsolving.leetcode.java.dailychallenge;

import java.util.ArrayList;
import java.util.List;

class LongestUnequalAdjacentGroupsSubsequencePartOne {

    /**
     * The core principle behind the idea is that the group can only be 0 or 1.
     * We can construct the longest valid subsequence by selecting just one representative
     * element from each group of consecutive identical values. For example, given the input:
     *              [0,0,0,1,1,1,0,1,0,1,1,1]
     * we can break it into segments of consecutive identical elements:
     *              [[0,0,0],[1,1,1],[0],[1],[0],[1,1,1]]
     * To ensure adjacent elements in the resulting subsequence are different,
     * we select a single index from each segment. In order to maximize the subsequence length,
     * we must select exactly one index from every segment of identical elements.
     * At the same time, we append the corresponding string from words to the result.
     *
     * @param words
     * @param groups
     * @return
     */
    public List<String> getLongestSubsequence(String[] words, int[] groups) {
        var result = new ArrayList<String>();
        result.add(words[0]);
        for (int i = 1; i < words.length; i++) {
            if (groups[i] != groups[i - 1]) {
                result.add(words[i]);
            }
        }

        return result;
    }
}
