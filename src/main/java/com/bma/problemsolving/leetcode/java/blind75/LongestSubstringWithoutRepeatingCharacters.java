package com.bma.problemsolving.leetcode.java.blind75;

import java.util.HashSet;

public class LongestSubstringWithoutRepeatingCharacters {

    public int lengthOfLongestSubstring(String s) {
        if (s.length() == 1) return 1;
        var window = new HashSet<Character>();

        int left = 0;
        int right = 0;
        int longestSeq = 0;
        while (right < s.length()) {
            if (window.contains(s.charAt(right))) {
                window.remove(s.charAt(left));
                left++;
            } else {
                window.add(s.charAt(right));
                right++;
            }

            longestSeq = Math.max(longestSeq, right - left);
        }

        return longestSeq;
    }
}
