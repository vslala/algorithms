package com.bma.problemsolving.leetcode.java.citadel;

public class PalindromicSubstrings {

    public int countSubstrings(String s) {
        int ans = 0;
        for (int i = 0; i < s.length(); i++) {
            ans += expandFromCenterAndCount(s, i, i);
            ans += expandFromCenterAndCount(s, i, i + 1);
        }

        return ans;
    }

    private int expandFromCenterAndCount(String s, int left, int right) {
        int count = 0;
        while (left >= 0 && right < s.length()) {
            if (s.charAt(left) != s.charAt(right)) {
                break;
            }

            left--;
            right++;
            count++;
        }

        return count;
    }


}
