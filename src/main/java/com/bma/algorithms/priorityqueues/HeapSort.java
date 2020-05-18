package com.bma.algorithms.priorityqueues;

import com.bma.algorithms.sort.Sort;
import com.bma.algorithms.sort.elementary.Util;

public class HeapSort implements Sort {

    private final int[] input;
    private long totalTime;

    public HeapSort(int[] input) {
        this.input = input;
    }

    @Override
    public void print() {
        Util.println(input);
    }

    @Override
    public long time() {
        Util.println("<HeapSort> Total Execution Time: " + totalTime + "ms");
        return totalTime;
    }

    @Override
    public void sort() {
        long startMillis = System.currentTimeMillis();
        int size = input.length - 1;

        for (int currentNode = size / 2; currentNode > -1; currentNode--) {
            sink(size, currentNode);
        }

        while (size > -1) {
            Util.swap(input, size, 0);
            size--;
            sink(size, 0);
        }

        totalTime = System.currentTimeMillis() - startMillis;
    }

    private void sink(int size, int currentNode) {
        int index = currentNode;
        while (index <= size) {
            int firstChild = (index * 2) + 1;
            int secondChild = firstChild + 1;

            if (secondChild <= size && Util.less(input[firstChild], input[secondChild])) {
                if (Util.less(input[index], input[secondChild])) {
                    Util.swap(input, index, secondChild);
                    index = secondChild;
                } else {
                    break;
                }
            } else if (firstChild <= size && Util.less(input[index], input[firstChild])) {
                Util.swap(input, index, firstChild);
                index = firstChild;
            } else {
                break;
            }
        }

    }

    public static void main(String[] args) {
        int[] input = Util.generateUnsortedArray(10000000); //{10, 9, 8, 7, 6, 5, 4, 3, 2, 1};
        HeapSort heapSort = new HeapSort(input);
        heapSort.sort();
//        heap  Sort.print();
        heapSort.time();
    }
}
