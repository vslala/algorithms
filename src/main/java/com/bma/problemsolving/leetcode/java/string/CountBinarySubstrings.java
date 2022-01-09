package com.bma.problemsolving.leetcode.java.string;

import com.bma.algorithms.sort.elementary.Util;

/**
 * 696. Count Binary Substrings
 * Give a binary string s, return the number of non-empty substrings that have the same number of 0's and 1's, and all the 0's and all the 1's in these substrings are grouped consecutively.
 * Substrings that occur multiple times are counted the number of times they occur.
 * @author varun.shrivastava
 */
class CountBinarySubstrings {
    public int count(String s) {
        int[] group = new int[s.length()];
        group[0] = 1;
        int idx = 0;

        for (int i = 1; i < group.length; i++) {
            Util.print("Index: " + i + ": "); Util.println(group);
            if (s.charAt(i - 1) == s.charAt(i)) {
                group[idx]++;
            } else {
                idx++;
                group[idx] = 1;
            }
        }

        int result = 0;
        for (int i = 1; i < idx + 1; i++) {
            result += Math.min(group[i - 1], group[i]);
        }

        return result;
    }
}
