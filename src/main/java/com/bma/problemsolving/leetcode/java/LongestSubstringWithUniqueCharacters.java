package com.bma.problemsolving.leetcode.java;

import java.util.Collections;
import java.util.HashSet;

/**
 * This is a classic sliding window algorithm
 *
 * Steps to perform:
 *
 * 1. Take a left pointer and right pointer.
 * 2. Take a hashset or array to keep track of the seen characters
 * 3. Take 'max' variable and initialize it to 1, since this is the default output if the string is not empty
 * 4. Scan through the given string from left to right (char-by-char)
 *      - if the char is not seen before, then
 *          - mark the char as seen (add it in the hashset)
 *          - increment the right pointer
 *          - calc the substring length by `right - left`.
 *      - if the char is seen before, then
 *          - remove the 'left' char from the hashset
 *          - increment the 'left'
 *
 */
public class LongestSubstringWithUniqueCharacters {
    public int lengthOfLongestSubstring(String str) {
        if (str.isEmpty() || str.isBlank()) return 0;
        // initialize max to 1 because if the string is not empty then this is the default
        int max = 1;

        var mem = new HashSet<>(Collections.singletonList(str.charAt(0)));

        int left = 0;
        int right = 1;
        while (right < str.length()) {
            if (!mem.contains(str.charAt(right))) {
                mem.add(str.charAt(right));
                right++;
                max = Math.max(max, right - left);
            } else {
                mem.remove(str.charAt(left));
                left++;
            }
        }

        return max;
    }
}
