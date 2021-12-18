package com.bma.algorithms.disjointsets;

public class QuickUnion implements DisjointSet {

    int[] id;

    public QuickUnion(int size) {
        id = new int[size];
    }

    private int root(int number) {
        while (number != id[number])
            number = id[number];
        return number;
    }

    @Override
    public boolean connected(int p, int q) {
        // if roots of both the numbers are same then they are connected.
        if (root(p) == root(q))
            return true;
        return false;
    }

    @Override
    public void union(int p, int q) {
        int rootP = root(p);
        int rootQ = root(q);
        id[rootP] = rootQ;
    }

    public static void main(String[] args) {
        DisjointSet quickFind = new QuickUnion(10);
        quickFind.union(3,5);
        quickFind.union(6,5);
        quickFind.union(9,4);

        System.out.println(quickFind.connected(9, 4));
    }
}
