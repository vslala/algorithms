package com.bma.problemsolving.leetcode.java.dynamicprogramming;

import com.bma.algorithms.sort.elementary.Util;

/**
 * 91. Decode Ways
 * ---------------
 * A message containing letters from A-Z can be encoded into numbers using the following mapping:
 * <p>
 * 'A' -> "1"
 * 'B' -> "2"
 * ...
 * 'Z' -> "26"
 * To decode an encoded message, all the digits must be grouped then mapped back into letters using the reverse of the mapping above (there may be multiple ways). For example, "11106" can be mapped into:
 * <p>
 * "AAJF" with the grouping (1 1 10 6)
 * "KJF" with the grouping (11 10 6)
 * Note that the grouping (1 11 06) is invalid because "06" cannot be mapped into 'F' since "6" is different from "06".
 * <p>
 * Given a string s containing only digits, return the number of ways to decode it.
 * <p>
 * The answer is guaranteed to fit in a 32-bit integer.
 *
 * @author varun.shrivastava
 */
class DecodeWays {
    public int numDecodings(String s) {
        var dp = new int[s.length() + 1];
        dp[0] = 1;
        dp[1] = s.charAt(0) == '0' ? 0 : 1;

        for (var i = 2; i <= s.length(); i++) {
            var oneDigit = Integer.parseInt(s.substring(i - 1, i));  // get current character
            var twoDigits = Integer.parseInt(s.substring(i - 2, i)); // get two characters from current

            if (oneDigit > 0) {
                Util.println("One Digit: " + oneDigit + ", Decoded Char: " + (char) (64 + oneDigit));
                dp[i] += dp[i - 1];
            }

            if (twoDigits > 9 && twoDigits < 27) {
                Util.println("Two Digits: " + twoDigits + ", Decoded Char: " + (char) (64 + twoDigits));
                dp[i] += dp[i - 2];
            }
        }

        return dp[s.length()];
    }
}
