package com.bma.problemsolving.codeforces;

public class NQueensProblem {
    private int count = 0;
    private boolean[] column;
    private boolean[] diag1;
    private boolean[] diag2;
    private int n;

    public NQueensProblem(int n) {
        this.n = n;
        column = new boolean[n];
        diag1 = new boolean[n * n];
        diag2 = new boolean[n  * n];
    }

    public int findSolutions() {
        search(0);
        return count;
    }

    private void search(int y) {
        if (y == n) {
            count++;
            return;
        }

        for (int x = 0; x < n; x++) {
            if (column[x] || diag1[x + y] || diag2[x - y + n - 1]) continue;

            column[x] = diag1[x + y] = diag2[x  - y + n - 1] = true;
            search(y + 1);
            column[x] = diag1[x + y] = diag2[x  - y + n - 1] = false;
        }
    }
}
