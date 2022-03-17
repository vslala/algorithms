package com.bma.problemsolving.meta.array;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * You are given a list of string, group them if they are same after using Ceaser Cipher Encrpytion.
 * Definition of "same", "abc" can right shift 1, get "bcd", here you can shift as many time as you want, the string will be considered as same.
 *
 * Input: ["abc", "bcd", "acd", "dfg"]
 * Output: [["abc", "bcd"], ["acd", "dfg"]]
 *
 * @author varun.shrivastava
 */
class GroupCaesarEncryption {
    private static final String ALPAHABETS = "abcdefghijklmnopqrstuvwxyz";
    private Map<List<Integer>, List<String>> map;

    /**
     * Time Complexity: O(n * k)
     *
     * Steps to group the caesar encryption:
     * 1. Calculate index for each char of each word using ALPHABETS.indexOf(s.charAt(i));
     * 2. Find minimum index in Step 1 (both 1 and 2 could be combined for optimization)
     * 3. Calculate new indices by (positions[i] - minimumIndex)
     * 4. Add it to the map
     *
     * @param input
     * @return
     */
    public List<List<String>> group(List<String> input) {
        map = new HashMap<>();
        for (String word: input) {// O(n)
            List<Integer> positions = calcPositions(word); // O(k)
            int min = findMin(positions); // O(k)
            List<Integer> newPositions = calcNewPositions(positions, min); // O(k)
            map.computeIfAbsent(newPositions, x -> new ArrayList<>()).add(word); // O(1)
        }

        return new ArrayList<>(map.values());
    }

    private List<Integer> calcNewPositions(List<Integer> positions, int min) {
        List<Integer> newPositions = new ArrayList<>();
        for (Integer position : positions) {
            newPositions.add(position - min);
        }

        return newPositions;
    }

    private int findMin(List<Integer> positions) {
        int min = Integer.MAX_VALUE;
        for (Integer position : positions) {
            min = Math.min(position, min);
        }

        return min;
    }

    private List<Integer> calcPositions(String word) {
        List<Integer> positions = new ArrayList<>();
        for (char c: word.toCharArray()) {
            positions.add(ALPAHABETS.indexOf(c));
        }

        return positions;
    }
}
