package com.bma.algorithms.disjointsets;

/**
 * This datastructure is better than QuickFind because it performs 'n' operations equal to the depth of the tree
 * for both union and find. Worst case complexity is O(n)
 *
 * Initialize   | Union     | Find
 * O(n)         | O(n)      | O(n)
 *
 * @author varun.shrivastava
 */
public class QuickUnion implements DisjointSet {

    private final int[] id;

    public QuickUnion(int size) {
        int totalVertices = size + 1;
        id = new int[totalVertices];

        // set id of each object to itself
        // (N array accesses)
        for (int i = 0; i < totalVertices; i++) {
            id[i] = i;
        }
    }

    /**
     * chase parent pointer until reach root
     * (depth of 'i' array accesses)
     * Time complexity = O(depth of i)
     *
     * @param i
     * @return
     */
    private int root(int i) {
        // while the parent is not same as the vertex,
        // keep tracking the parent pointer
        while (i != id[i])
            i = id[i];

        return i;
    }

    /**
     * check if p and q have same root
     * (depth of p and q array accesses)
     * Time complexity = O(depth of p + depth of q)
     *
     * @param p
     * @param q
     * @return
     */
    @Override
    public boolean connected(int p, int q) {
        // if roots of both the numbers are same then they are connected.
        return root(p) == root(q);
    }

    /**
     * Change root of p to point to root of q
     * (depth of p and q array accesses)
     * Time complexity = O(depth of p + depth of q)
     *
     * @param p
     * @param q
     */
    @Override
    public void union(int p, int q) {
        int rootP = root(p);
        int rootQ = root(q);
        id[rootP] = rootQ;
    }
}
