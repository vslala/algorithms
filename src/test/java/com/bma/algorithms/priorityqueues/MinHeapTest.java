package com.bma.algorithms.priorityqueues;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MinHeapTest {

    private MinHeap<Integer> min;

    @BeforeEach
    void setup() {
        min = new MinHeap<>();
        min.add(10);
        min.add(9);
        min.add(8);
        min.add(7);
        min.add(6);
        min.add(5);
    }

    @Test
    void itShouldAddElementsToTheQueue() {
        assertEquals(6, min.size());
    }

    // null,5,7,6,10,8,9
    @Test
    void itShouldRemoveTheMinItemFromTheHeap() {
        assertEquals(5, min.remove());
        assertEquals(6, min.remove());
        assertEquals(7, min.remove());
        assertEquals(8, min.remove());
        assertEquals(9, min.remove());
    }

    @Test
    void itShouldPrintTheHeap() {
        min.print();
    }

}