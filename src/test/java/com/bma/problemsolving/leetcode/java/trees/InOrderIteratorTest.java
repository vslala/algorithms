package com.bma.problemsolving.leetcode.java.trees;

import com.bma.algorithms.misc_datastructures.BinaryTree;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class InOrderIteratorTest {

    @Test
    void shouldIterateTheTreeInOrder() {
        // Given:
        BinaryTree binaryTree = new BinaryTree(List.of(4, 2, 5, 1, 3));
        var expected = List.of(1, 2, 3, 4, 5);

        // When: iteration is done
        InOrderIterator inOrderIterator = new InOrderIterator(binaryTree);
        var output = new ArrayList<Integer>();
        while (inOrderIterator.hasNext()) output.add(inOrderIterator.next().getVal());

        // Then:
        assertEquals(expected, output);
    }
}