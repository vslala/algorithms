package com.bma.algorithms.disjointsets;

/**
 * This algorithm maintains the height of each tree.
 * Therefore, whenever a new union is made, it checks for the height of both components,
 * and attaches the shorter one to the longer component.
 *
 * This prevents tree height to grow hence improves the time complexity.
 *
 * Initialize   | Union         | Find
 * O(n)         | log(n)        | log(n)
 */
class QuickUnionByRank implements DisjointSet {

    private int[] rank;
    private int[] id;
    private int totalVertices;

    public QuickUnionByRank(int size) {
        this.totalVertices = size + 1;
        this.rank = new int[totalVertices];
        this.id = new int[totalVertices];

        for (int i = 0; i < totalVertices; i++) {
            id[i] = i;
            rank[i] = 1;
        }
    }

    @Override
    public boolean connected(int p, int q) {
        return root(p) == root(q);
    }

    private int root(int v) {
        while (id[v] != v)
            v = id[v];

        return v;
    }

    @Override
    public void union(int p, int q) {
        int pid = root(p);
        int qid = root(q);
        for (int i = 0; i < totalVertices; i++) {
            if (rank[pid] > rank[qid]) {
                id[qid] = pid;
            } else if (rank[pid] < rank[qid]) {
                id[pid] = qid;
            } else {
                id[qid] = pid;
                rank[pid] += 1;
            }
        }
    }
}
