package com.bma.problemsolving.leetcode.java.blind75;

import java.util.HashMap;
import java.util.Map;

public class MaxOccurrencesSubstring {

    private static class Window {
        private final Map<String, Integer> freqCounter;
        private final int[] cc = new int[26];
        private final int minSize;
        private final int maxLetters;
        private final StringBuilder sb;
        private int distinctCount = 0;
        private int maxSubstrCount = 0;

        public Window(int maxLetters, int minSize, int maxSize) {
            this.maxLetters = maxLetters;
            this.minSize = minSize;
            this.sb = new StringBuilder();
            this.freqCounter = new HashMap<>();
        }

        /**
         Adds a character to the sliding window and updates frequency statistics.
         *
         * Intuition:
         * We maintain a fixed-size sliding window of `minSize` characters as we move through the input string.
         * - When the window is full, we slide it forward by removing the first character.
         * - We keep track of how many *distinct characters* are inside the window using a character count array.
         *
         * The key rule is: a substring is valid only if the number of distinct characters in the window
         * is less than or equal to `maxLetters`. If it is valid:
         * - We convert the window into a string and count how many times we've seen it.
         * - We update the maximum occurrence count (`ans`) seen so far.
         *
         * This method ensures that every substring of length `minSize` is considered efficiently,
         * without checking longer substrings which are less likely to repeat.
         * @param ch
         */
        public void add(char ch) {
            if (sb.length() == this.minSize) {
                char c = removeFirst();
                cc[c - 'a']--;
                if (cc[c - 'a'] == 0) {
                    distinctCount--;
                }
            }

            sb.append(ch);
            if (cc[ch - 'a'] == 0) {
                distinctCount++;
            }
            cc[ch - 'a']++;

            if (sb.length() == minSize && distinctCount <= maxLetters) {
                String key = sb.toString();
                int subStringCount = freqCounter.getOrDefault(key, 0) + 1;
                freqCounter.put(key, subStringCount);
                maxSubstrCount = Math.max(maxSubstrCount, subStringCount);
            }

        }

        private char removeFirst() {
            char c = sb.charAt(0);
            sb.deleteCharAt(0);
            return c;
        }

        public int count() {
            return maxSubstrCount;
        }
    }

    public int maxFreq(String s, int maxLetters, int minSize, int maxSize) {
        var window = new Window(maxLetters, minSize, maxSize);
        for (char ch: s.toCharArray()) {
            window.add(ch);
        }
        return window.count();
    }
}
