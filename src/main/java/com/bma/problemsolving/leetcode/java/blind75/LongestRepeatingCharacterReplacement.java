package com.bma.problemsolving.leetcode.java.blind75;

import java.util.HashMap;

class LongestRepeatingCharacterReplacement {
    /**
     * The core principle to solve this problem is to find the biggest substring where max_count for freq of each
     * character subtracted by the size of the current window is less than equal to k.
     * `window_size - max_count <= k`
     *
     * @param s provide string of characters
     * @param k numer of replacements allowed
     * @return maximum substring that can be formed after using k replacements
     */
    public int characterReplacement(String s, int k) {
        var freqMap = new HashMap<Character, Integer>();
        var window = new StringBuilder();
        int maxFreqInWindow = 0;
        int biggestWindow = 0;
        var longestSubstr = "";

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            window.append(c);
            freqMap.put(c, freqMap.getOrDefault(c, 0) + 1);
            maxFreqInWindow = Math.max(maxFreqInWindow, freqMap.get(c));

            if (window.length() - maxFreqInWindow > k) {
                // Shrink window from the left
                char firstChar = window.charAt(0);
                freqMap.put(firstChar, freqMap.get(firstChar) - 1);
                window.deleteCharAt(0);
            }

            biggestWindow = Math.max(biggestWindow, window.length());
            if (biggestWindow > longestSubstr.length()) {
                longestSubstr = window.toString();
            }
        }

        System.out.printf("LongestSubstring: %s%n", longestSubstr);
        return biggestWindow;
    }

}
