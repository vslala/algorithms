package com.bma.algorithms.leetcode;

import com.bma.fixtures.Fixtures;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.stream.IntStream;
import java.util.stream.LongStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FindNthTermFromEndInTheLinkedListTest {

    FindNthTermFromEndInTheLinkedList<Integer> testClass = new FindNthTermFromEndInTheLinkedList<>();

    @BeforeEach
    public void setup() {
        System.out.println("Adding Numbers...");
        Fixtures.measureRunTime(() -> IntStream.iterate(1, x -> x + 1).limit(10000000).forEach(i -> testClass.add(i)));
    }

    @Test
    public void it_should_add_the_new_nodes_at_the_end() {
        assertEquals(10000, testClass.size());
    }

    @Test
    public void it_should_return_nth_node_value_from_the_last() {
        System.out.println("Measuring nth from the last...");
        Fixtures.measureRunTime(() -> testClass.nthFromLast(10));
        assertEquals(9999500, (int) testClass.nthFromLast(500));
    }

    @Test
    public void it_should_compare_the_times_with_actual_linked_list() {
        testClass = null;
        LinkedList<Long> list = new LinkedList<>();
        System.out.println("Adding Numbers to actual linked list...");
        Fixtures.measureRunTime(() -> LongStream.iterate(1, x -> x + 1).limit(10000000).forEach(list::add));
    }

}