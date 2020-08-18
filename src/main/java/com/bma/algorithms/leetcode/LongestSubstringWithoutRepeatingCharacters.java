package com.bma.algorithms.leetcode;

import java.util.HashSet;
import java.util.Set;

public class LongestSubstringWithoutRepeatingCharacters {



    public int lengthOfLongestSubstring(String s) {
        int longestLen = 0;
        int currLen = 0;
        Set<Character> charSet = new HashSet<Character>();

        for (char c: s.toCharArray()) {
            if (charSet.contains(c)) {
                charSet.clear();
                if (longestLen < currLen) longestLen = currLen;
                currLen = 0;
            }

            currLen ++;
            charSet.add(c);

        }

        return longestLength(longestLen, currLen);
    }

    private int longestLength(int longestLen, int currLen) {
        return longestLen > currLen ? longestLen : currLen;
    }

    private boolean isLongestSubstringSoFar(int longestLen, int currLen) {
        return longestLen < currLen;
    }
}
