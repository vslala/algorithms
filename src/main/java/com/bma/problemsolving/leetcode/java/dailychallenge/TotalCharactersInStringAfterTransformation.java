package com.bma.problemsolving.leetcode.java.dailychallenge;

class TotalCharactersInStringAfterTransformation {
    /**
     * Dynamic Programming
     * -------
     * The core principle is to keep track of each character count and when the char `z` is reached
     * increment the count of `a` and `b` while setting the `z` count to 0
     *
     * @param s
     * @param t
     * @return
     */
    public int lengthAfterTransformations(String s, int t) {
        final int MOD = 1000000007;
        int[] alphabets = new int[26];
        for (char c: s.toCharArray()) {
            alphabets[c - 'a'] += 1;
        }

        for (int i = 0; i < t; i++) {
            int zFreq = alphabets[25];
            for (int j = 25; j > 0; j--) {
                alphabets[j] = alphabets[j - 1];
            }

            alphabets[0] = 0;
            alphabets[0] = (alphabets[0] + zFreq) % MOD;
            alphabets[1] = (alphabets[1] + zFreq) % MOD;
        }

        int len = 0;
        for (int i = 0; i < 26; i++) {
            len = (len + alphabets[i]) % MOD;
        }
        return len;
    }
}
