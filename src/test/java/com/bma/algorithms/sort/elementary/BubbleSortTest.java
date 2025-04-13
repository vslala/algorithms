package com.bma.algorithms.sort.elementary;

import org.junit.jupiter.api.Test;

import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class BubbleSortTest {

    private int[]  input = {4,3,1,2,5};
    private BubbleSort bubbleSort = new BubbleSort(input);

    @Test
    public void itShouldSortTheArrayInAscendingOrder() {
        bubbleSort.sort();
        bubbleSort.print();
        bubbleSort.time();

        IntStream.range(0, input.length - 1 ).forEach(index -> {
            assertTrue(input[index + 1] > input[index]);
        });
    }

}