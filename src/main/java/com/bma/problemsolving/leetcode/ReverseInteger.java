package com.bma.problemsolving.leetcode;

public class ReverseInteger {


    public Integer reverse(Integer input) {
        int output = 0;
        while (input != 0) {
            int pop = input % 10;
            input = input / 10;
            // edge cases
            if (output > Integer.MAX_VALUE/10 || (output == Integer.MAX_VALUE / 10 && pop > 7)) return 0;
            if (output < Integer.MIN_VALUE/10 || (output == Integer.MIN_VALUE / 10 && pop < -8)) return 0;

            output = output * 10 + pop;
        }

        return output;
    }
}
