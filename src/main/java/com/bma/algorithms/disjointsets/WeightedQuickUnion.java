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
class WeightedQuickUnion implements DisjointSet {

    private int[] height;
    private int[] id;
    private int totalVertices;

    public WeightedQuickUnion(int size) {
        this.totalVertices = size + 1;
        this.height = new int[totalVertices];
        this.id = new int[totalVertices];

        for (int i = 0; i < totalVertices; i++) {
            id[i] = i;
            height[i] = 1;
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

    /**
     * Check which component has more depth,
     * append the smaller tree/component to the longer tree.
     * By doing so it won't increase the tree size.
     * If both components have same height then add any component to the other one,
     * but don't forget to increase the depth of the component.
     *
     * @param p
     * @param q
     */
    @Override
    public void union(int p, int q) {
        int pRoot = root(p);
        int qRoot = root(q);
        if (height[pRoot] > height[qRoot]) {
            id[qRoot] = id[pRoot];
        } else if (height[qRoot] > height[pRoot]) {
            id[pRoot] = id[qRoot];
        } else {
            id[qRoot] = id[pRoot];
            height[pRoot] += 1;
        }
    }
}
