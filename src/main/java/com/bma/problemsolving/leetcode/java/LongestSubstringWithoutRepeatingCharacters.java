package com.bma.problemsolving.leetcode.java;

import java.util.HashSet;
import java.util.Set;

public class LongestSubstringWithoutRepeatingCharacters {

    public int lengthOfLongestSubstring(String s) {
        int longestLen = 0;
        int currLen = 0;
        Set<Character> charSet = new HashSet<>();

        for (char c: s.toCharArray()) {
            if (charSet.contains(c)) {
                charSet.clear();
                longestLen = Math.max(longestLen, currLen);
                currLen = 0;
            }

            currLen ++;
            charSet.add(c);

        }

        return Math.max(longestLen, currLen);
    }

}
