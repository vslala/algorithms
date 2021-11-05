package com.bma.problemsolving.leetcode.java.string;

import java.util.HashSet;

/**
 * 3. Longest Substring Without Repeating Characters
 * Given a string s, find the length of the longest substring without repeating characters.
 * Good problem for implementing sliding window
 *
 * @author varun.shrivastava
 */
public class LongestSubstringWithoutRepeatingCharacters {

    public int lengthOfLongestSubstring(String s) {
        var result = 0;
        var windowStartPointer = 0;
        var windowEndPointer = 0;
        var mem = new HashSet<Character>();

        while (windowEndPointer < s.length()) {
            if (mem.contains(s.charAt(windowEndPointer))) {
                mem.remove(s.charAt(windowStartPointer));
                windowStartPointer++;
            } else {
                mem.add(s.charAt(windowEndPointer));
                windowEndPointer++;
                result = Math.max(result, mem.size());
            }
        }

        return result;
    }

}
