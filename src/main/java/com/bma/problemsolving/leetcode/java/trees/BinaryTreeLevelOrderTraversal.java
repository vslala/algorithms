package com.bma.problemsolving.leetcode.java.trees;

import com.bma.algorithms.misc_datastructures.BinaryTree;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

class BinaryTreeLevelOrderTraversal {
    public List<List<Integer>> levelOrder(BinaryTree.TreeNode root) {
        var result = new ArrayList<List<Integer>>();

        if (root == null) return result;

        var q = new LinkedList<BinaryTree.TreeNode>();

        q.offer(root);
        while (!q.isEmpty()) {
            // traverse current level
            var items = new ArrayList<Integer>();
            for (BinaryTree.TreeNode item : q) {
                items.add(item.getVal());
            }
            result.add(items);

            // add next level to queue
            var size = q.size();
            while (size > 0) {
                BinaryTree.TreeNode item = q.poll();
                if (item.getLeft() != null) {
                    q.offer(item.getLeft());
                }
                if (item.getLeft() != null) {
                    q.offer(item.getRight());
                }
                size--;
            }

        }

        return result;

    }
}
