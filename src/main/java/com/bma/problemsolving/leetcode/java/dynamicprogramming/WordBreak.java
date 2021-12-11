package com.bma.problemsolving.leetcode.java.dynamicprogramming;

import java.util.Arrays;
import java.util.List;

/**
 * 139. Word Break
 * Given a string s and a dictionary of strings wordDict, return true if s can be segmented into a space-separated sequence of one or more dictionary words.
 * Note that the same word in the dictionary may be reused multiple times in the segmentation.
 *
 * @author varun.shrivastava
 */
class WordBreak {
    public boolean bottomUpDPApproach(String s, List<String> wordDict) {
        boolean[] dp = new boolean[s.length()];

        for (int charIndex = 0; charIndex < s.length(); charIndex++) {
            for (String word : wordDict) {
                if (charIndex >= word.length() - 1
                        && (charIndex == word.length() - 1 || dp[charIndex - word.length()])
                        && s.substring(charIndex - word.length() + 1, charIndex + 1).equals(word)) {
                    dp[charIndex] = true;
                    break;
                }
            }
        }

        return dp[s.length() - 1];
    }

    public boolean topDownMemoizationApproach(String s, List<String> wordDict) {
        return new TopDownApproach(s, wordDict).result();
    }

    private static class TopDownApproach {
        private final List<String> wordDict;
        private final String s;
        private final int[] mem;

        public TopDownApproach(String s, List<String> wordDict) {
            this.s = s;
            this.wordDict = wordDict;
            this.mem = new int[s.length()];
            Arrays.fill(mem, -1);
        }

        private boolean dp(int i) {
            if (i < 0) return true;

            if (mem[i] == -1) {
                for (String word: wordDict) {
                    if (i >= word.length() - 1 && dp(i - word.length())) {
                        if (s.substring(i - word.length() + 1, i + 1).equals(word)) {
                            mem[i] = 1;
                            break;
                        }
                    }
                }
            }

            if (mem[i] == -1) {
                mem[i] = 0;
            }

            return mem[i] == 1;
        }

        public boolean result() {
            return dp(s.length() - 1);
        }
    }
}
