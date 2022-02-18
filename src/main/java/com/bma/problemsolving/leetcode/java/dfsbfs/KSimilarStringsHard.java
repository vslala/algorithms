package com.bma.problemsolving.leetcode.java.dfsbfs;

import com.bma.algorithms.sort.elementary.Util;

import java.util.*;

/**
 * 854. K-Similar Strings
 * Strings s1 and s2 are k-similar (for some non-negative integer k)
 * if we can swap the positions of two letters in s1 exactly k times so that the resulting string equals s2.
 *
 * Given two anagrams s1 and s2, return the smallest k for which s1 and s2 are k-similar.
 *
 * Example 1:
 *
 * Input: s1 = "ab", s2 = "ba"
 * Output: 1
 * Example 2:
 *
 * Input: s1 = "abc", s2 = "bca"
 * Output: 2
 */
class KSimilarStringsHard {

    public int kSimilarity(String s1, String s2) {
        Deque<String> q = new LinkedList<>();
        Set<String> visited = new HashSet<>();

        q.offer(s1);
        int level = 0;
        while (!q.isEmpty()) {
            int size = q.size();
            while (size-- > 0) {
                String item = q.poll();

                assert item != null;
                if (item.equals(s2)) return level;

                for (String neighbour: neighbours(item, s2)) {
                    if (!visited.contains(neighbour)) {
                        q.offer(neighbour);
                        visited.add(neighbour);
                    }
                }
            }

            level++;
        }

        return -1;
    }

    /**
     * This is a two-way algorithm to find the relevant neighbours:
     * 1. Find the first index where chars do not match
     * 2. Swap only when s1[j] == s2[i]
     *
     * take the example: ab, bc
     * 1. i will be 0 (s1[0] != s2[0] => a != b)
     * 2. ab will become bc when we swap(s1[j] == s2[i])
     *
     * @param s1 first input string
     * @param s2 second input string
     * @return
     */
    private List<String> neighbours(String s1, String s2) {
        var output = new LinkedList<String>();

        int i = 0;
        for (; i < s1.length(); i++) {
            if (s1.charAt(i) != s2.charAt(i)) {
                break;
            }
        }

        for (int j = i + 1; j < s1.length(); j++) {
            if (s1.charAt(j) == s2.charAt(i)) {
                String neighbour = swap(s1, i, j);
                output.add(neighbour);
            }
        }

        Util.println(output);
        return output;
    }

    /**
     * This is a mutable function and does not change the input
     *
     * @param input String that needs to be swapped
     * @param i1 First index
     * @param i2 Second Index
     * @return
     */
    private String swap(String input, int i1, int i2) {
        char[] arr = input.toCharArray();
        char temp = arr[i1];
        arr[i1] = arr[i2];
        arr[i2] = temp;

        return new String(arr);
    }
}
