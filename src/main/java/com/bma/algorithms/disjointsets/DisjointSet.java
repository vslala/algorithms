package com.bma.algorithms.disjointsets;

public interface DisjointSet {
    boolean connected(int p, int q);
    void union(int p, int q);
}
