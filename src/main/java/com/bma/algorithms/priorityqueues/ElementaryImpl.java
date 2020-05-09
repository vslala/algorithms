package com.bma.algorithms.priorityqueues;

import com.bma.algorithms.sort.elementary.Util;

// Find the min number from the list
public class ElementaryImpl {

    private int input[];

    public ElementaryImpl(int[] input) {
        this.input = input;
    }

    public void delMin() {
        int min = Integer.MAX_VALUE;
        for (int index = 0; index < input.length; index++)
            if (input[index] < min) {
                min = input[index];
                Util.swap(input, input.length-1, index);
            }

        Util.println(input);

        // delete the last index
        int aux[] = new int[input.length-1];
        for (int index = 0; index < aux.length; index++)
            aux[index] = input[index];

        input = aux;
    }

    public void print() {
        Util.println(input);
    }

    public static void main(String[] args) {
        int input[] = Util.generateUnsortedArray(10);
        Util.println(input);
        ElementaryImpl elementary = new ElementaryImpl(input);
        elementary.delMin();
        elementary.print();
    }
}
