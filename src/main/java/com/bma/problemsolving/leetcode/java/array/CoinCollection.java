package com.bma.problemsolving.leetcode.java.array;

class CoinCollection {

    public int minCoinFlips(String coinCollection) {
        int headCount = 0;
        for (int i = 0; i < coinCollection.length(); i++) {
            if (coinCollection.charAt(i) == 'H') {
                headCount++;
            }
        }

        int result = headCount;
        int rightHead = result;
        int leftTail = 0;
        for (int i = 0; i < coinCollection.length(); i++) {
            if (coinCollection.charAt(i) == 'H') {
                rightHead--;
            } else {
                leftTail++;
            }

            result = Math.min(result, leftTail + rightHead);
        }

        return result;
    }
}
