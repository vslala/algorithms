package com.bma.problemsolving.leetcode.java.disjointsets;


/**
 * 547. Number of Provinces
 * There are n cities. Some of them are connected, while some are not.
 * If city a is connected directly with city b, and city b is connected directly with city c, then city a is connected indirectly with city c.
 * A province is a group of directly or indirectly connected cities and no other cities outside of the group.
 * You are given an n x n matrix isConnected where isConnected[i][j] = 1 if the ith city and the jth city are directly connected, and isConnected[i][j] = 0 otherwise.
 * Return the total number of provinces.
 *
 * @author varun.shrivastava
 */
class NumberOfProvinces {
    public int findCircleNum(int[][] isConnected) {
        int totalVertices = isConnected.length;
        WeightedQuickUnionWithPathCompression quickUnion = new WeightedQuickUnionWithPathCompression(totalVertices);
        for (int i = 0; i < totalVertices; i++) {
            for (int j = 0; j < totalVertices; j++) {
                if (i != j && isConnected[i][j] == 1) {
                    quickUnion.union(i, j);
                }
            }
        }

        return quickUnion.count();
    }

    private static class WeightedQuickUnionWithPathCompression {
        private int count;
        private final int[] parent;
        private final int[] height;

        public WeightedQuickUnionWithPathCompression(int totalVertices) {
            count = totalVertices;
            parent = new int[totalVertices];
            height = new int[totalVertices];

            for (int i = 0; i < totalVertices; i++) {
                parent[i] = i;
                height[i] = 1;
            }
        }

        public int count() {
            return count;
        }


        public void union(int p, int q) {
            int rootP = root(p);
            int rootQ = root(q);

            if (rootP == rootQ) return;

            if (height[rootP] < height[rootQ]) {
                parent[rootP] = rootQ;
                height[rootQ] += height[rootP];
            } else {
                parent[rootQ] = rootP;
                height[rootP] += height[rootQ];
            }

            count--;
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
    }
}
