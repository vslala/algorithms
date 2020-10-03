package com.bma.algorithms.priorityqueues;

import com.bma.algorithms.sort.elementary.Util;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class BinaryHeapsTest {

    @Test
    public void insert_the_item_at_the_end_of_the_tree() {
        BinaryHeaps<String> binaryHeaps = new BinaryHeaps<>();
        binaryHeaps.insert("A");
        binaryHeaps.insert("B");
        binaryHeaps.insert("C");
        binaryHeaps.insert("D");
        binaryHeaps.insert("E");
        binaryHeaps.insert("A");
        binaryHeaps.insert("B");
        binaryHeaps.insert("C");
        binaryHeaps.insert("D");
        binaryHeaps.insert("E");
        assertEquals(11, binaryHeaps.size());
    }

    @Test
    public void insert_the_item_in_tree_and_swim_the_item_to_its_correct_position() {
        BinaryHeaps<String> binaryHeaps = new BinaryHeaps<>();
        binaryHeaps.insert("A");
        binaryHeaps.insert("B");
        binaryHeaps.insert("C");
        binaryHeaps.insert("D");
        binaryHeaps.insert("E");
        binaryHeaps.insert("A");
        binaryHeaps.insert("B");
        binaryHeaps.insert("C");
        binaryHeaps.insert("D");
        binaryHeaps.insert("E");

        Util.println(binaryHeaps.get());

        List<String> heap = binaryHeaps.get();
        assertFalse(Util.less(heap, 1, 2));
        assertFalse(Util.less(heap, 2, 2*2));
        assertFalse(Util.less(heap, 3, 3*2));
    }

    @Test
    public void delete_max_item_from_the_heap() {
        BinaryHeaps<String> binaryHeaps = new BinaryHeaps<>();
        binaryHeaps.insert("A");
        binaryHeaps.insert("B");
        binaryHeaps.insert("C");
        binaryHeaps.insert("D");
        binaryHeaps.insert("E");
        binaryHeaps.insert("A");
        binaryHeaps.insert("B");
        binaryHeaps.insert("C");
        binaryHeaps.insert("D");
        binaryHeaps.insert("E");
        binaryHeaps.insert("Z");

        Util.println(binaryHeaps.get());

        binaryHeaps.deleteMax();

        List<String> heap = binaryHeaps.get();

        Util.println(binaryHeaps.get());

        assertFalse(Util.less(heap, 1, 2));
        assertFalse(Util.less(heap, 2, 2*2));
        assertFalse(Util.less(heap, 3, 3*2));
    }

}