package com.bma.problemsolving.leetcode.java.dfsbfs;

import com.bma.algorithms.misc_datastructures.BinaryTree;
import com.bma.algorithms.sort.elementary.Util;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * 199. Binary Tree Right Side View
 * Given an m x n binary matrix mat, return the distance of the nearest 0 for each cell.
 * The distance between two adjacent cells is 1.
 *
 * @author varun.shrivastava
 */
public class BinaryTreeRightSideView {
    public List<Integer> rightSideView(BinaryTree.TreeNode root) {
        if (root == null) return Collections.emptyList();
        var result = new ArrayList<Integer>();
        var q = new LinkedList<BinaryTree.TreeNode>();
        q.add(root);

        while (! q.isEmpty()) {
            var size = q.size();
            BinaryTree.TreeNode curr = null;

            // accessing elements of that level
            for (int i = 0; i < size; i++) {
                curr = q.remove();
                Util.print(curr.getVal() + ",");

                if (curr.getLeft() != null) {
                    q.add(curr.getLeft());
                }

                if (curr.getRight() != null) {
                    q.add(curr.getRight());
                }
            }
            Util.println("------------------------");

            result.add(curr.getVal());
        }

        return result;

    }
}
