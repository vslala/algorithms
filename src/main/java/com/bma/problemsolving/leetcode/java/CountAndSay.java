package com.bma.problemsolving.leetcode.java;

public class CountAndSay {
    public static final String STR_END = "$";

    public String countAndSay(int n) {
        if (n == 1) return "1";

        var tmp = new StringBuilder();
        var output = "1" + STR_END;

        for (int i = 0; i < n - 1; i++) {
            tmp.setLength(0);
            int left = 0;
            int right = 1;
            while (right < output.length()) {
                final char rightChar = output.charAt(right);
                final char leftChar = output.charAt(left);
                if (rightChar == '$') {
                    int count = right - left;
                    output = tmp.append(count)
                            .append(leftChar)
                            .append(STR_END)
                            .toString();
                    break;
                }

                if (leftChar != rightChar) {
                    int count = right - left;
                    tmp.append(count).append(leftChar);
                    left = right;
                }

                right++;
            }
        }

        return output.substring(0, output.length() - 1);
    }
}
