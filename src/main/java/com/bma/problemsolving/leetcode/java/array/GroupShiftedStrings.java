package com.bma.problemsolving.leetcode.java.array;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 249. Group Shifted Strings
 * We can shift a string by shifting each of its letters to its successive letter.
 * - For example, "abc" can be shifted to be "bcd".
 *
 * We can keep shifting the string to form a sequence.
 * - For example, we can keep shifting "abc" to form the sequence: "abc" -> "bcd" -> ... -> "xyz".
 *
 * Given an array of strings strings, group all strings[i] that belong to the same shifting sequence. You may return the answer in any order.
 *
 * @author varun.shrivastava
 */
class GroupShiftedStrings {

    public List<List<String>> groupStrings(String[] strings) {
        Map<String, List<String>> group = new HashMap<>();

        for (String str: strings) {
            String diffKey = generateKey(str);
            group.computeIfAbsent(diffKey, x -> new ArrayList<>())
                    .add(str);
        }

        List<List<String>> output = new ArrayList<>();
        group.forEach((key, val) -> output.add(val));

        return output;
    }

    /**
     * Generates a key based on the difference between each character and string length
     * For Ex - abc, bcd -> 1_1_3, 1_1_3
     *          az, ba -> 25_2, 25_2
     *          abc, bc -> 1_1_3, 1_2
     *
     * @param str input string
     * @return key
     */
    private String generateKey(String str) {
        StringBuilder sb = new StringBuilder();
        for (int i = str.length() - 1; i > 0; i--) {
            int diff = str.charAt(i) - str.charAt(i - 1);
            if (diff < 0) diff += 26;
            sb.append(diff).append("_");
        }

        sb.append(str.length());

        return sb.toString();
    }


}

