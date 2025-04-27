package com.bma.algorithms.misc_datastructures;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BinaryTreeTest {

    @Test
    void itShouldCreateBinaryTreeFromTheArrayList() {
        var binaryTree = new BinaryTree(List.of(3, 9, 20, 15, 7));
        binaryTree.print();
        BinaryTree.TreeNode node = binaryTree.get();
        assertEquals(3, node.getVal());
        assertEquals(9, node.getLeft().getVal());
        assertEquals(20, node.getRight().getVal());
    }

    @Test
    void itShouldCreateBinaryTreeFromTheArrayListUsingNulls() {
        var binaryTree = new BinaryTree(Arrays.asList(3, 9, 20, null, null, 15, 7));
        BinaryTree.TreeNode node = binaryTree.get();
        assertEquals(3, node.getVal());
        assertEquals(9, node.getLeft().getVal());
        assertEquals(20, node.getRight().getVal());
        assertEquals(15, node.getRight().getLeft().getVal());
    }
}
