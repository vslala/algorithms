package com.bma.problemsolving.leetcode.java.dailychallenge;

import java.util.*;

/**
 * Solution for the LeetCode problem "Lexicographically Minimum String After Removing Stars".
 *
 * <p>Given a string that contains lowercase letters and '*' characters, each '*'
 * can remove one preceding letter of your choice. This class computes
 * the lexicographically smallest resulting string after optimally applying all removals.</p>
 *
 * <p>Example:
 * <pre>
 *   Input:  "dac*ba*"
 *   Process:
 *     - At first '*', remove 'a' (smallest before it): "dc*ba*"
 *     - At second '*', remove 'b': "dc*a*"
 *     - And so on...
 *   Output: "dca"
 * </pre>
 * </p>
 *
 * @author Varun
 * @github www.github.com/vslala
 * @date Updated: 07/06/2025
 */
public class LexicographicallyMinimumStringAfterRemovingStars {

    /**
     * Removes each '*' and one chosen preceding character so that the final
     * string is lexicographically smallest.
     *
     * @param input the original string of lowercase letters and '*' characters
     * @return the lexicographically smallest string after all removals
     */
    public String clearStars(String input) {
        TreeMap<Character, TreeSet<Integer>> charPositions = buildCharIndicesMap(input);
        List<Integer> starPositions = getStarIndices(input);
        boolean[] removed = new boolean[input.length()];

        for (int starIdx : starPositions) {
            for (Map.Entry<Character, TreeSet<Integer>> entry : charPositions.entrySet()) {
                TreeSet<Integer> positions = entry.getValue();
                Integer toRemoveIdx = positions.floor(starIdx - 1);
                if (toRemoveIdx != null) {
                    removed[starIdx] = true;
                    removed[toRemoveIdx] = true;
                    positions.remove(toRemoveIdx);
                    if (positions.isEmpty()) {
                        charPositions.remove(entry.getKey());
                    }
                    break;  // move on to next star
                }
            }
        }

        StringBuilder result = new StringBuilder(input.length());
        for (int i = 0; i < input.length(); i++) {
            if (!removed[i]) {
                result.append(input.charAt(i));
            }
        }
        return result.toString();
    }

    /**
     * Builds a sorted map from each non-star character to a sorted set
     * of its indices in the input string.
     *
     * @param input the original string
     * @return a TreeMap mapping each character to a TreeSet of its indices
     */
    private TreeMap<Character, TreeSet<Integer>> buildCharIndicesMap(String input) {
        TreeMap<Character, TreeSet<Integer>> map = new TreeMap<>();
        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            if (c == '*') {
                continue;
            }
            map.computeIfAbsent(c, k -> new TreeSet<>()).add(i);
        }
        return map;
    }

    /**
     * Gathers all positions of '*' characters in the input string.
     *
     * @param input the original string
     * @return a list of indices where '*' appears, in ascending order
     */
    private List<Integer> getStarIndices(String input) {
        List<Integer> stars = new ArrayList<>();
        for (int i = 0; i < input.length(); i++) {
            if (input.charAt(i) == '*') {
                stars.add(i);
            }
        }
        return stars;
    }
}
