package com.bma.algorithms.sort.merge;

import com.bma.algorithms.sort.Sort;
import com.bma.algorithms.sort.elementary.Util;

public class MergeSort implements Sort {

    private int[] input;
    private long totalTime;

    public MergeSort(int[] input) {
        this.input = input;
    }

    @Override
    public void print() {
        Util.println(input);
    }

    @Override
    public void time() {
        Util.print("Total Time Taken: " + totalTime + "ms");
    }

    @Override
    public void sort() {
        long startMillis = System.currentTimeMillis();
        input = sort(input);
        totalTime = System.currentTimeMillis() - startMillis;
    }

    private int[] sort(int[] input) {
        // recursive call breaking condition
        if (input.length <= 1) return input;

        int midPoint = input.length / 2;
        int[] left = new int[midPoint];
        int[] right = new int[input.length%2 == 0 ? midPoint: midPoint+1];

        // populate left array
        for (int i=0; i<left.length; i++) left[i] = input[i];
        // populate right array
        for (int j=0; j<right.length; j++) right[j] = input[midPoint + j];


        left = sort(left);
        right = sort(right);
        return merge(left,right);
    }

    public int[] merge(int[] left, int[] right) {
        int[] result = new int[left.length + right.length];

        int leftPointer = 0;
        int rightPointer = 0;
        int resultPointer = 0;

        while (leftPointer < left.length || rightPointer < right.length)
            if (leftPointer < left.length && rightPointer < right.length)
                if (left[leftPointer] < right[rightPointer]) result[resultPointer++] = left[leftPointer++];
                else result[resultPointer++] = right[rightPointer++];
            else if (leftPointer < left.length) result[resultPointer++] = left[leftPointer++];
            else if (rightPointer < right.length) result[resultPointer++] = right[rightPointer++];

        return result;
    }


    public static void main(String[] args) {
        int[] input = new int[]{10,9,8,7,6,5,4,3,2,1}; // Util.generateArrayInput(10000000)
        com.bma.algorithms.sort.Sort sort = new MergeSort(Util.generateUnsortedArray(10000000));
        sort.sort();
//        sort.print();
        sort.time();
    }
}
