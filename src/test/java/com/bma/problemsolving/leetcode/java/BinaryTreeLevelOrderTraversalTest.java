package com.bma.problemsolving.leetcode.java;

import com.bma.algorithms.misc_datastructures.BinaryTree;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BinaryTreeLevelOrderTraversalTest {

    @Test
    void itShouldPerformLevelOrderTraversalOnTheGivenBinaryTree() {
        var binaryTree = new BinaryTree(List.of(3, 9, 20, 15, 7));
        var levelOrderTraversal = new BinaryTreeLevelOrderTraversal(binaryTree);
        List<List<Integer>> output = levelOrderTraversal.get();

        assertEquals(output, List.of(
                List.of(3),
                List.of(9, 20),
                List.of(15, 7))
        );
    }

}