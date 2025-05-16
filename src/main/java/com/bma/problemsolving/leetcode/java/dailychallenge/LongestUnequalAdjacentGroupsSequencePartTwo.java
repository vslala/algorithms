package com.bma.problemsolving.leetcode.java.dailychallenge;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * @author vslala
 * @github www.github.com/vslala
 */
class LongestUnequalAdjacentGroupsSequencePartTwo {
    /**
     * The main idea behind this is the following:
     * Create a directed graph of word[j] -> word[i], iff,
     *      word[i].length == word[j].length
     *      hammingDistance(word[i], word[j]) == 1
     *      groups[i] != groups[j]
     * Find the longest path from starting word to the end word
     * Since the above steps will take time to construct the graph,
     * topological sort on the graph and then finding the longest distance between two words.
     * This could be short-circuited with Dynamic Programming.
     * Since we don't need to build a topologically sorted graph as its already in a sequence in an array.
     * To keep track of the longest path, we create a `prev` array that keeps the reference to the index that points to the current word.
     * @param words
     * @param groups
     * @return
     */
    public List<String> getWordsInLongestSubsequence(String[] words, int[] groups) {
        if (words.length == 0) {
            return List.of();
        }

        if (words.length == 1) {
            return List.of(words[0]);
        }

        // holds the length of sequence that ends at a given index
        int[] dp = new int[words.length];
        // holds the reference of a prev index that points a current index in graph
        int[] prev = new int[words.length];

        Arrays.fill(dp, 1);
        Arrays.fill(prev, -1);

        for (int i = 1; i < dp.length; i++) {
            for (int j = 0; j < i; j++) {
                String first = words[i];
                String second = words[j];
                if (sameLength(first, second) && hammingDistance(first, second) == 1 && isDifferentGroup(groups, i, j)) {
                    if (dp[j] + 1 > dp[i]) {
                        dp[i] = dp[j] + 1;
                        prev[i] = j;
                    }
                }
            }
        }

        int maxIndex = 0;
        for (int i = 1; i < dp.length; i++) {
            if (dp[i] > dp[maxIndex]) {
                maxIndex = i;
            }
        }

        LinkedList<String> result = new LinkedList<>();
        while (maxIndex != -1) {
            result.addFirst(words[maxIndex]);
            maxIndex = prev[maxIndex];
        }


        return result;
    }

    private boolean isDifferentGroup(int[] groups, int i, int j) {
        return groups[i] != groups[j];
    }

    private int hammingDistance(String first, String second) {
        assert first.length() == second.length();
        int distance = 0;
        for (int i = 0; i < first.length(); i++) {
            if (first.charAt(i) != second.charAt(i)) {
                distance++;
            }
        }

        return distance;
    }

    private boolean sameLength(String first, String second) {
        return first.length() == second.length();
    }
}
