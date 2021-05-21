package com.bma.algorithms.misc_datastructures;

import com.bma.algorithms.sort.elementary.Util;
import lombok.Data;

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
    private TreeNode temp;
    public BinaryTree(List<Integer> numbers) {
        root = new TreeNode(numbers.get(0));

        boolean isFirst = true;
        for (Integer number : numbers) {
            if (isFirst) {
                isFirst = false;
                continue;
            }
            // setting temp to root node
            temp = root;

            while (true) {
                if (temp.left == null) {
                    temp.left = new TreeNode(number);
                    break;
                } else if (temp.right == null) {
                    temp.right = new TreeNode(number);
                    break;
                } else {
                    temp = temp.left;
                }
            }

        }
    }

    public void print() {
        print(root);
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
