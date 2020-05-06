package com.bma.algorithms.quick.find;

import com.bma.algorithms.quick.IQuickFind;

public class QuickFindUF implements IQuickFind {

    int[] id;

    public QuickFindUF(int number) {
        id = new int[number];
    }

    @Override
    public boolean connected(int p, int q) {
        return id[p] == id[q];
    }

    @Override
    public void union(int p, int q) {
        int pid = id[p];
        int qid = id[q];
        for (int index = 0; index < id.length; index++) {
            if (connected(id[index], q))
                id[index] = qid;
        }
    }

    public static void main(String[] args) {
        QuickFindUF quickFindUF = new QuickFindUF(10);
        quickFindUF.union(3,5);
        quickFindUF.union(6,5);
        quickFindUF.union(9,4);

        System.out.println(quickFindUF.connected(9, 4));
    }
}
