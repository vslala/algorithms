package com.bma.algorithms.disjointsets;

/**
 * This is an eager datastructure of finding connected components
 *
 * Initialize   | Union     | Find
 * O(N)         | O(N)      | O(1)
 *
 * @author varun.shrivastava
 */
public class QuickFind implements DisjointSet {

    private final int totalVertices;
    private final int[] id;

    public QuickFind(int numOfVertices) {
        totalVertices = numOfVertices + 1;
        id = new int[totalVertices];
        // set id of each object to itself (N array access)
        for (int i = 0; i < totalVertices; i++) {
            id[i] = i;
        }
    }

    /**
     * Time Complexity: O(1)
     * This operation is performed in constant time with QuickFind data structure
     * @param p
     * @param q
     * @return
     */
    @Override
    public boolean connected(int p, int q) {
        return id[p] == id[q];
    }

    /**
     * Time complexity for this is O(n)
     * Change all entries with id[p] to id[q]
     * (At most 2N + 2 array access)
     * @param p
     * @param q
     */
    @Override
    public void union(int p, int q) {
        int pid = id[p];
        int qid = id[q];

        for (int v = 0; v < totalVertices; v++) {
            if (id[v] == pid)
                id[v] = qid;
        }
    }
}
