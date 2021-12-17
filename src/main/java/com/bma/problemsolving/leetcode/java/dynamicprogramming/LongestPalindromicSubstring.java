package com.bma.problemsolving.leetcode.java.dynamicprogramming;

/**
 * 5. Longest Palindromic Substring
 * Given a string s, return the longest palindromic substring in s.
 * https://leetcode.com/problems/longest-palindromic-substring/
 *
 * @author varun.shrivastava
 */
public class LongestPalindromicSubstring {

    private int resultStart = 0;
    private int resultLength = 0;

    public String longestPalindrome(String s) {
        for (var i = 0; i < s.length(); i++) {
            checkPalindromeByExpandingRange(s, i, i);
            checkPalindromeByExpandingRange(s, i, i + 1);
        }

        return s.substring(resultStart, resultStart + resultLength);
    }

    /**
     * Expanding Range O(1) both space and time complexity solution
     * @param s
     * @param start
     * @param end
     */
    private void checkPalindromeByExpandingRange(String s, int start, int end) {
        while (start >= 0 && end < s.length() && s.charAt(start) == s.charAt(end)) {
            start--;
            end++;
        }

        var palindromeLength = end - start - 1;
        if (palindromeLength > resultLength) {
            resultLength = palindromeLength;
            resultStart = start + 1;
        }
    }

    /**
     * Dynamic Program Solution
     * @param s
     * @return
     */
    public String longestPalindromeDP(String s) {
        boolean[][] dp = new boolean[s.length()][s.length()];
        for (int gap = 0; gap < s.length(); gap++) {
            for (int i = 0, j = gap; j < dp.length; i++, j++) {
                if (gap == 0) {
                    dp[i][j] = true;    // string of 1 char is a palindrome
                } else {
                    var isCharAtBothEndEqual = s.charAt(i) == s.charAt(j);
                    if (gap == 1) {
                        dp[i][j] = isCharAtBothEndEqual;  // if both char are same then the string is palindrome
                    } else {
                        dp[i][j] = isCharAtBothEndEqual && dp[i + 1][j - 1];
                    }
                }

                var palindromeLength = j - i + 1;
                if (dp[i][j] && palindromeLength > resultLength) {
                    resultStart = i;
                    resultLength = palindromeLength;
                }
            }
        }

        return s.substring(resultStart, resultStart + resultLength);
    }

    /**
     * Recursive Memoized solution
     * # of subproblems = input.length
     * time to solve one subproblem = O(1) + O(n) to check palindrome
     * Time Complexity = O(n2)
     * Can be optimized further
     * @param input
     * @return
     */
    public String longestPalindromeRecursive(String input) {
        int[][] mem = new int[input.length()][input.length()];
        int longestPalindrome = memo(0, input.length() - 1, mem, input);

        for (int i = 0; i < mem.length; i++) {
            for (int j = 0; j < mem[i].length; j++) {
                if (mem[i][j] == longestPalindrome && isPalindrome(input, i, j)) {
                    return input.substring(i, j + 1);
                }
            }
        }

        return input;
    }

    private boolean isPalindrome(String input, int left, int right) {
        while (left <= right) {
            if (input.charAt(left++) != input.charAt(right--)) return false;
        }

        return true;
    }

    private int memo(int left, int right, int[][] mem, String s) {
        if (mem[left][right] != 0) return mem[left][right];

        if (left == right) {
            mem[left][right] = 1;
            return 1;
        }

        if (s.charAt(left) == s.charAt(right)) {
            if (left + 1 == right) {
                mem[left][right] = 2;
            } else {
                mem[left][right] = 2 + memo(left + 1, right - 1, mem, s);
            }
            return mem[left][right];
        }


        mem[left][right] = Math.max(memo(left + 1, right, mem, s), memo(left, right - 1, mem, s));
        return mem[left][right];
    }
}
