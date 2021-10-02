package com.bma.problemsolving.leetcode.java;

import com.bma.algorithms.misc_datastructures.BinaryTree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BinaryTreeLevelOrderTraversal {

    private final BinaryTree binaryTree;

    public BinaryTreeLevelOrderTraversal(BinaryTree binaryTree) {
        this.binaryTree = binaryTree;
    }

    public List<List<Integer>> get() {
        List<List<Integer>> output = new ArrayList<>();
        BinaryTree.TreeNode root = binaryTree.get();
        Queue<BinaryTree.TreeNode> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            var innerList = new ArrayList<Integer>();
            int totalNodeAtThisLevel = queue.size();
            for (var i = 0; i < totalNodeAtThisLevel; i++) {
                BinaryTree.TreeNode node = queue.remove();
                innerList.add(node.getVal());
                if (node.getLeft() != null) queue.add(node.getLeft());
                if (node.getRight() != null) queue.add(node.getRight());
            }

            output.add(innerList);
        }

        return output;
    }
}
