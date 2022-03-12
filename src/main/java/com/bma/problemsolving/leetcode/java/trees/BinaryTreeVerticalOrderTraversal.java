package com.bma.problemsolving.leetcode.java.trees;

import com.bma.algorithms.misc_datastructures.BinaryTree;
import org.javatuples.Pair;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 314. Binary Tree Vertical Order Traversal
 * Given the root of a binary tree, return the vertical order traversal of its nodes' values. (i.e., from top to bottom, column by column).
 * If two nodes are in the same row and column, the order should be from left to right.
 */
class BinaryTreeVerticalOrderTraversal {
    public List<List<Integer>> verticalOrder(BinaryTree.TreeNode root) {
        List<List<Integer>> output = new ArrayList<>();
        Map<Integer, List<Integer>> vMap = new HashMap<>();
        Deque<Pair<Integer, BinaryTree.TreeNode>> queue = new ArrayDeque<>();

        queue.offer(Pair.with(0, root));
        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size-- > 0) {
                var item = queue.poll();
                assert item != null;
                int level = item.getValue0();
                BinaryTree.TreeNode node = item.getValue1();

                List<Integer> items = vMap.getOrDefault(level, new ArrayList<>());
                items.add(node.getVal());
                vMap.put(level, items);

                if (node.getLeft() != null) queue.offer(Pair.with(level - 1, node.getLeft()));
                if (node.getRight() != null) queue.offer(Pair.with(level + 1, node.getRight()));
            }
        }

        Set<Integer> keys = vMap.keySet();
        List<Integer> sortedKeys = keys.stream().sorted().collect(Collectors.toList());
        for (int key: sortedKeys) output.add(vMap.get(key));

        return output;
    }
}
