package com.bma.problemsolving.codewars;

import static java.lang.String.format;

/*
URL: https://www.codewars.com/kata/54c27a33fb7da0db0100040e/train/java
 */
public class Square {

    public static String isSquare(int n) {
        if (n < 0) return "negative numbers aren't square numbers";

        int sqrt = (int) Math.sqrt(n);
        return sqrt * sqrt == n ?
                format("%d is a square number (%d * %d)", n, sqrt, sqrt)
                : format("%d isn't a square number", n);
    }
}
