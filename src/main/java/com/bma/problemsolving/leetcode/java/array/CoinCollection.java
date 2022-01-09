package com.bma.problemsolving.leetcode.java.array;

import com.bma.algorithms.sort.elementary.Util;

/**
 * Given a coin collection, find the minimum number of flips to form the beatiful sequence.
 * A beautiful sequence is of the form "HH...TTT" (heads followed by tail)
 * <p>
 * This type of question is solved using Prefix Sums
 * Evidently, it comes down to a question of knowing, for each candidate half: how many 'T's are in the left half, and how many 'H's are in the right half.
 * We can use prefix sums. Say P[i+1] = A[0] + A[1] + ... + A[i], where A[i] = T if S[i] == 'T', else A[i] = H. We can calculate P in linear time.
 * Then if we want x zeros followed by N-x ones, there are P[x] ones in the start that must be flipped, plus (N-x) - (P[N] - P[x]) zeros that must be flipped.
 * The last calculation comes from the fact that there are P[N] - P[x] ones in the later segment of length N-x, but we want the number of zeros.
 *
 * @author varun.shrivastava
 */
class CoinCollection {

    public int minCoinFlipsUsingPrefixSumStorage(String coinCollection) {
        int length = coinCollection.length();
        int[] prefixSum = new int[length + 1];
        for (int i = 0; i < length; i++)
            prefixSum[i + 1] = prefixSum[i] + (coinCollection.charAt(i) == 'T' ? 1 : 0);

        Util.println(prefixSum);

        /*
        Algorithm
        For example, with S = "HTHTTH": we have P = [0, 0, 1, 1, 2, 3, 3]. Now say we want to evaluate having x=3 TAILs.
        There are P[3] = 1 ones in the first 3 characters, and P[6] - P[3] = 2 TAILs in the later N-x = 3 characters.
        So, there is (N-x) - (P[N] - P[x]) = 1 HEAD in the later N-x characters.
        We take the minimum among all candidate answers to arrive at the final answer.
         */
        int result = Integer.MAX_VALUE;
        for (int j = 0; j <= length; j++) {
            result = Math.min(result, prefixSum[j] + length - j - (prefixSum[length] - prefixSum[j]));
        }

        return result;
    }

    public int minCoinFlipsUsingTwoVariables(String coinCollection) {
        int headCount = 0;
        int flipCount = 0;

        for (int i = 0; i < coinCollection.length(); i++) {
            if (coinCollection.charAt(i) == 'T') headCount += 1;
            else flipCount += 1;
            flipCount = Math.min(headCount, flipCount);
        }

        return flipCount;
    }

    public int minCoinFlipsUsingThreeVariables(String coinCollection) {
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
