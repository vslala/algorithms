package com.bma.algorithms.codeforces;

import java.util.List;

public class AlmostSorted {


    public String check(List<Integer> input, String expectedOutput) {
        boolean isSorted = true;
        int i = 0;
        for (i=1; i < input.size() - 1; i++) {
            if  (input.get(i) < input.get(i - 1)) {
                isSorted = false;
                break;
            }
        }

        return isSorted ? "yes": "no";
    }
}
