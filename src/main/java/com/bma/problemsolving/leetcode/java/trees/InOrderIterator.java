package com.bma.problemsolving.leetcode.java.trees;

import com.bma.algorithms.misc_datastructures.BinaryTree;

import java.util.ArrayDeque;
import java.util.Deque;

class InOrderIterator {
    private Deque<BinaryTree.TreeNode> stack;
    private BinaryTree.TreeNode curr;

    public InOrderIterator(BinaryTree tree) {
        curr = tree.get();
        stack = new ArrayDeque<>();
    }

    public boolean hasNext() {
        while (curr != null) {
            stack.push(curr);
            curr = curr.getLeft();
        }

        return !stack.isEmpty();
    }


    public BinaryTree.TreeNode next() {
        BinaryTree.TreeNode node = stack.pop();
        curr = node.getRight();

        return node;
    }
}
