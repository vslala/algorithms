package com.bma.problemsolving.leetcode.java.dynamicprogramming;

/**
 * You are given an integer array coins representing coins of different denominations and an integer amount representing a total amount of money.
 * Return the number of combinations that make up that amount. If that amount of money cannot be made up by any combination of the coins, return 0.
 * You may assume that you have an infinite number of each kind of coin.
 * The answer is guaranteed to fit into a signed 32-bit integer.
 *
 * @author varun.shrivastava
 */
class CoinChangeTwo {

    public int change(int amount, int[] coins) {
        int[] combinations = new int[amount + 1];
        combinations[0] = 1;

        for (int coin : coins) {
            for (int x = coin; x <= amount; x++) {
                combinations[x] = combinations[x] + combinations[x - coin];
            }
        }

        return combinations[amount];
    }
}
