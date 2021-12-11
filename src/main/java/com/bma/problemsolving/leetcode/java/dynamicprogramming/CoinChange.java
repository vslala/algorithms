package com.bma.problemsolving.leetcode.java.dynamicprogramming;

import java.util.Arrays;

/**
 * 322. Coin Change
 * You are given an integer array coins representing coins of different denominations and an integer amount representing a total amount of money.
 * Return the fewest number of coins that you need to make up that amount. If that amount of money cannot be made up by any combination of the coins, return -1.
 * You may assume that you have an infinite number of each kind of coin.
 * Example 1:
 * Input: coins = [1,2,5], amount = 11
 * Output: 3
 * Explanation: 11 = 5 + 5 + 1
 * <p>
 * Example 2:
 * Input: coins = [2], amount = 3
 * Output: -1
 * <p>
 * Example 3:
 * Input: coins = [1], amount = 0
 * Output: 0
 * <p>
 * Example 4:
 * Input: coins = [1], amount = 1
 * Output: 1
 * <p>
 * Example 5:
 * Input: coins = [1], amount = 2
 * Output: 2
 *
 * @author varun.shrivastava
 */
class CoinChange {

    public static ICoinChange getAlgorithm(String algorithm) {
        ICoinChange memoization = new MemoizationSolution();
        ICoinChange dp = new DPSolution();

        switch (algorithm) {
            case "MEMO":
                return memoization;
            case "DP":
            default:
                return dp;
        }
    }

    interface ICoinChange {
        int coinChange(int[] coins, int amount);
    }

    private static class DPSolution implements ICoinChange {

        @Override
        public int coinChange(int[] coins, int amount) {
            int max = amount + 1;
            int[] dp = new int[max];
            Arrays.fill(dp, max);

            dp[0] = 0;
            for (int target = 0; target <= amount; target++) {
                for (int coin : coins) {
                    if (coin <= target) {
                        dp[target] = Math.min(dp[target], dp[target - coin] + 1);
                    }
                }
            }

            return dp[amount] > amount ? -1 : dp[amount];
        }
    }

    private static class MemoizationSolution implements ICoinChange {
        private int[] coins;
        private int[] mem;

        public int coinChange(int[] coins, int amount) {
            this.coins = coins;
            this.mem = new int[amount + 1];

            return memo(amount);
        }

        private int memo(int target) {
            if (target < 0) {
                return -1;
            }
            if (target == 0) {
                return 0;
            }
            if (mem[target] != 0) return mem[target];

            int min = Integer.MAX_VALUE;
            for (int coin : coins) {
                int pick = memo(target - coin);
                if (pick >= 0 && pick < min) {
                    min = 1 + pick;
                }
            }

            mem[target] = min == Integer.MAX_VALUE ? -1 : min;
            return mem[target];
        }
    }
}
