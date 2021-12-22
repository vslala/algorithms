package com.bma.problemsolving.leetcode.java.disjointsets;

import java.util.Arrays;
import java.util.Comparator;

/**
 * There are n people in a social group labeled from 0 to n - 1. You are given an array logs where logs[i] = [timestampi, xi, yi] indicates that xi and yi will be friends at the time timestampi.
 * Friendship is symmetric. That means if a is friends with b, then b is friends with a. Also, person a is acquainted with a person b if a is friends with b, or a is a friend of someone acquainted with b.
 * Return the earliest time for which every person became acquainted with every other person. If there is no such earliest time, return -1.
 *
 * @author varun.shrivastava
 */
class EarliestMomentWhenEveryoneBecomeFriends {
    private int[] parent;
    private int[] height;
    private int count;

    public int earliestAcq(int[][] logs, int n) {
        Arrays.sort(logs, Comparator.comparingInt(t -> t[0]));

        count = n;
        parent = new int[n];
        height = new int[n];
        for (int i = 0; i < n; i++) {
            parent[i] = i;
            height[i] = 1;
        }

        for (int[] log : logs) {
            int t = log[0];
            int a = log[1];
            int b = log[2];

            union(a, b);
            if (count == 1) {
                return t;
            }
        }

        return -1;
    }

    private int root(int p) {
        int root = p;
        while (root != parent[root]) {
            root = parent[root];
        }

        while (p != root) {
            int newP = parent[p];
            parent[p] = root;
            p = newP;
        }

        return root;
    }

    private void union(int p, int q) {
        int rootP = root(p);
        int rootQ = root(q);
        if (rootP == rootQ) return;
        if (parent[rootP] < rootQ) {
            parent[rootP] = rootQ;
            height[rootQ] += height[rootP];
        } else {
            parent[rootQ] = rootP;
            height[rootP] += height[rootQ];
        }
        count--;
    }
}
