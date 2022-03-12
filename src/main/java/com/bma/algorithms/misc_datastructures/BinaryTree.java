package com.bma.algorithms.misc_datastructures;

import com.bma.algorithms.sort.elementary.Util;
import lombok.Data;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;

public class BinaryTree {

    @Data
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode() {}
        TreeNode(int val) { this.val = val; }
        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    private TreeNode root;
    public BinaryTree(List<Integer> numbers) {
        root = new TreeNode(numbers.get(0));

        Deque<TreeNode> queue = new ArrayDeque<>();
        queue.offer(root);

        int index = 1;
        while (!queue.isEmpty()) {
            Integer val1 = index >= numbers.size() ? null : numbers.get(index++);
            Integer val2 = index >= numbers.size() ? null : numbers.get(index++);

            TreeNode node = queue.poll();
            assert node != null;
            if (val1 != null) {
                node.left = new TreeNode(val1);
                queue.offer(node.left);
            }

            if (val2 != null) {
                node.right = new TreeNode(val2);
                queue.offer(node.right);
            }
        }
    }

    public void print() {
        Util.println("Performing Level Order Traversal...");
        Deque<TreeNode> queue = new ArrayDeque<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            while (size-- > 0) {
                TreeNode node = queue.poll();
                assert node != null;
                Util.print(node.val + ",");

                if (node.left != null) queue.offer(node.left);
                if (node.right != null) queue.offer(node.right);
            }
            Util.println();
        }
    }

    private void print(TreeNode root) {
        if (root != null) {
            Util.print(root.val);
            print(root.left);
            print(root.right);
        }
    }

    public TreeNode get() {
        return root;
    }
}
