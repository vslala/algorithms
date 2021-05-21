package com.bma.algorithms.misc_datastructures;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class BinaryTreeTest {

    @Test
    void itShouldCreateBinaryTreeFromTheArrayList() {
        var binaryTree = new BinaryTree(List.of(3, 9, 20, 15, 7));
        BinaryTree.TreeNode node = binaryTree.get();
        assertEquals(3, node.getVal());
        assertEquals(9, node.getLeft().getVal());
        assertEquals(20, node.getRight().getVal());
    }
}
