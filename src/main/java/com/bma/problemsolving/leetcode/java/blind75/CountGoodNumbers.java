package com.bma.problemsolving.leetcode.java.blind75;

class CountGoodNumbers {
    private static final int MOD = 1_000_000_007;

    public int countGoodNumbers(long n) {
        long even = (n + 1) / 2;
        long odd = n / 2;

        return (int) (power(5, even) * power(4, odd) % MOD);
    }

    private long power(int x, long y) {
        long result = 1;
        long base = x;

        while (y > 0) {
            if (y % 2 == 1) {
                result = (result * base) % MOD;
            }
            base = (base * base) % MOD;
            y /= 2;
        }

        return result;
    }

}
