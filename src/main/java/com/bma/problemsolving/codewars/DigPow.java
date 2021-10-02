package com.bma.problemsolving.codewars;

import java.util.ArrayList;
import java.util.Collections;

/*
URL: https://www.codewars.com/kata/5552101f47fc5178b1000050/train/java
 */
public class DigPow {


    public static int digPow(int n, int p) {
        ArrayList<Integer> digits = breakNumberDigits(n);
        Collections.reverse(digits);

        int total = calcTotal(p, digits);

        return total % n == 0 ? total / n : -1;
    }

    private static int calcTotal(int p, ArrayList<Integer> digits) {
        int total = 0;
        for (int index = 0, power = p; index < digits.size(); power++, index++ )
            total += Math.pow(digits.get(index), power);
        return total;
    }

    private static ArrayList<Integer> breakNumberDigits(int n) {
        var digits = new ArrayList<Integer>();

        int num = n;
        while (num > 0) {
            int remainder = num % 10;
            num /= 10;
            digits.add(remainder);
        }
        return digits;
    }
}
