package com.bma.problemsolving.leetcode.java.array;

import java.util.HashMap;

class MinimumWindowSubstring {
    public String minWindow(String s, String t) {
        var ans = "";

        final var map1 = new HashMap<Character, Integer>();
        for (char c: t.toCharArray()) {
            map1.put(c, map1.getOrDefault(c, 0) + 1);
        }

        var map2 = new HashMap<Character, Integer>();

        var left = 0;
        var right = 0;
        var matchCount = 0;
        var desiredMatchCount = t.length();
        while (true) {
            var f1 = false;
            var f2 = false;

            // s = "ADOBECODEBANC", t = "ABC"
            // acquire chars to the right till the match count becomes equal to the desired match count
            while (left < s.length() && matchCount != desiredMatchCount) {
                var c = s.charAt(left);
                map2.put(c, map2.getOrDefault(c, 0) + 1);

                if (map2.get(c) <= map1.getOrDefault(c, 0)) {
                    matchCount++;
                }

                left++;
                f1 = true;
            }

            while (right < left && matchCount == desiredMatchCount) {
                var curr = s.substring(right, left);
                if (ans.length() == 0 || curr.length() < ans.length()) {
                    ans = curr;
                }

                var c = s.charAt(right);
                map2.put(c, map2.get(c) - 1);
                if (map2.get(c) < map1.getOrDefault(c, 0)) {
                    matchCount--;
                }

                right++;

                f2 = true;
            }

            if (!f1 && !f2) {
                break;
            }
        }

        return ans;
    }
}
