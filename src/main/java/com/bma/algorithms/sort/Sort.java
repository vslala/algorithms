package com.bma.algorithms.sort;

import com.bma.algorithms.sort.elementary.InsertionSort;
import com.bma.algorithms.sort.elementary.SelectionSort;
import com.bma.algorithms.sort.elementary.ShellSort;
import com.bma.algorithms.sort.merge.BottomUpMergeSort;
import com.bma.algorithms.sort.merge.MergeSort;
import com.bma.algorithms.sort.quick.QuickSort;

public interface Sort {


    void print();

    long time();

    void sort();


    static long mergeSort(int input[]) {
        MergeSort mergeSort = new MergeSort(input);
        mergeSort.sort();
        return mergeSort.time();
    }

    static long bottomUpMergeSort(int[] input) {
        BottomUpMergeSort bottomUpMergeSort = new BottomUpMergeSort(input);
        bottomUpMergeSort.sort();
        return bottomUpMergeSort.time();
    }

    static long quickSort(int input[]) {
        QuickSort quickSort = new QuickSort(input);
        quickSort.sort();
        return quickSort.time();
    }

    static long insertionSort(int input[]) {
        InsertionSort insertionSort = new InsertionSort(input);
        insertionSort.sort();
        return insertionSort.time();
    }

    static long shellSort(int input[]) {
        ShellSort shellSort = new ShellSort(input);
        shellSort.sort();
        return shellSort.time();
    }

    static long selectionSort(int input[]) {
        SelectionSort selectionSort = new SelectionSort(input);
        selectionSort.sort();
        return selectionSort.time();
    }
}
