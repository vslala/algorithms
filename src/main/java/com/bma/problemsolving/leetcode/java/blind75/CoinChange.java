package com.bma.problemsolving.leetcode.java.blind75;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * @author Varun Shrivastava
 * @github www.github.com/vslala
 * @date 16/05/2025
 */
class CoinChange {
    public int coinChange(int[] coins, int amount) {
        int INF = amount + 1;
        int[] nodes    = new int[amount + 1];
        int[] prevCoin = new int[amount + 1];
        Arrays.fill(nodes,    INF);
        Arrays.fill(prevCoin, -1);
        nodes[0] = 0;

        for (int i = 1; i <= amount; i++) { // ← “visit” node i
            for (int coin : coins) {        // ← explore each outgoing edge i->i–c
                if (coin <= i && nodes[i - coin] + 1 < nodes[i]) {
                    nodes[i]    = nodes[i - coin] + 1;
                    prevCoin[i] = coin;     // remember that using coin c was best
                }
            }
        }

        if (nodes[amount] == INF) return -1;

        var result = new ArrayList<Integer>();
        for (int i = amount; i > 0; i -= prevCoin[i]) {
            result.add(prevCoin[i]);
        }

        return result.size();
    }
}
