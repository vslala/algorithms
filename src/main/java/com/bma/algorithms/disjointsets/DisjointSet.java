package com.bma.algorithms.disjointsets;

public interface DisjointSet {
    boolean connected(int p, int q);

    void union(int p, int q);

    static DisjointSet quickFind(int totalVertices) {
        return new QuickUnion(totalVertices);
    }

    static DisjointSet quickUnion(int totalVertices) {
        return new QuickUnion(totalVertices);
    }

    static DisjointSet quickUnionWithPathCompression(int totalVertices) {
        return new WeightedQuickUnionWithPathCompression(totalVertices);
    }
}
