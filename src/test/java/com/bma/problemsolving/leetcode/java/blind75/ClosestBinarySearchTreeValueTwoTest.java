package com.bma.problemsolving.leetcode.java.blind75;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ClosestBinarySearchTreeValueTwoTest {
    @Test
    void should_return_the_top_key_closest_values_to_the_target_value() {
        var binaryTree = new ClosestBinarySearchTreeValueTwo.TreeNode(
                4,
                new ClosestBinarySearchTreeValueTwo.TreeNode(
                        2,
                        new ClosestBinarySearchTreeValueTwo.TreeNode(1),
                        new ClosestBinarySearchTreeValueTwo.TreeNode(3)
                ),
                new ClosestBinarySearchTreeValueTwo.TreeNode(
                        5
                )
        );

        var sol = new ClosestBinarySearchTreeValueTwo();
        List<Integer> output = sol.closestKValues(binaryTree, 3.714286, 2);

        assertEquals(2, output.size());
        assertTrue(output.contains(4));
        assertTrue(output.contains(3));
    }

    @Test
    void test_2_should_return_the_top_key_closest_values_to_the_target_value() {
        var binaryTree = new ClosestBinarySearchTreeValueTwo.TreeNode(1);

        var sol = new ClosestBinarySearchTreeValueTwo();
        List<Integer> output = sol.closestKValues(binaryTree, 0.0D, 1);

        assertEquals(1, output.size());
        assertTrue(output.contains(1));
    }
}