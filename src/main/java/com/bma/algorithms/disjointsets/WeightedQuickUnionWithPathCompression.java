package com.bma.algorithms.disjointsets;

/**
 * This algorithm further improves the time complexity for Weighted Union datastructure
 * The improvement here is to make every child of the sub-tree to point to the root node.
 * Then in all cases the tree only have single root and a height of 1
 * In best case, it requires O(1) to run both find() and union()
 * Average case can be denoted as the inverse ackerman function times N (number of vertices)
 *
 * Initialize   | Union         | Find
 * O(n)         | log(n)        | log(n)
 */
class WeightedQuickUnionWithPathCompression implements DisjointSet {

    private int[] parent;
    private int[] height;

    public WeightedQuickUnionWithPathCompression(int totalVertices) {
        totalVertices = totalVertices + 1;
        this.parent = new int[totalVertices];
        this.height = new int[totalVertices];

        for (int i = 0; i < totalVertices; i++) {
            parent[i] = i;
            height[i] = 1;
        }
    }

    @Override
    public boolean connected(int p, int q) {
        return root(p) == root(q);
    }

    private int root(int p) {
        int root = p;
        // find the root
        while (parent[root] != root) {
            root = parent[root];
        }

        // then recursively make every child point to root
        while (p != root) {
            int newP = parent[p];
            parent[p] = root;
            p = newP;
        }

        return root;
    }

    @Override
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
    }
}
