package com.bma.problemsolving.leetcode.java.dailychallenge;

import java.util.*;

class FindThreeDigitEvenNumbers {
    public int[] findEvenNumbers(int[] digits) {

        var result = new ArrayList<Integer>();
        var set =  new HashSet<Integer>();

        for (int i = 0; i < digits.length; i++) {
            if (digits[i] == 0) continue;

            for (int j = 0; j < digits.length; j++) {
                if (j == i) continue;
                for (int k = 0; k < digits.length; k++) {
                    if (k == i || k == j || digits[k] % 2 != 0) continue;
                    int num = digits[i] * 100 + digits[j] * 10 + digits[k];
                    if (set.contains(num)) continue;

                    result.add(num);
                    set.add(num);
                }
            }
        }

        int[] output = new int[result.size()];
        for (int i = 0; i < output.length; i++) {
            output[i] = result.get(i);
        }

        Arrays.sort(output);
        return output;
    }
}
