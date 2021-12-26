package com.bma.problemsolving.leetcode.java.dfsbfs;

import com.bma.problemsolving.leetcode.Model.Node;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * You are given a perfect binary tree where all leaves are on the same level, and every parent has two children. The binary tree has the following definition:
 *
 * struct Node {
 *   int val;
 *   Node *left;
 *   Node *right;
 *   Node *next;
 * }
 * Populate each next pointer to point to its next right node. If there is no next right node, the next pointer should be set to NULL.
 *
 * Initially, all next pointers are set to NULL.
 *
 * @author varun.shrivastava
 */
class PopulateNextPointerInATree {

    public Node connect(Node root) {
        Deque<Node> q = new LinkedList<>();
        q.offer(root);

        while (!q.isEmpty()) {
            int size = q.size();
            List<Node> level = new ArrayList<>();
            while (size > 0) {
                Node node = q.poll();
                assert node != null;

                if (node.getLeft() != null) level.add(node.getLeft());
                if (node.getRight() != null) level.add(node.getRight());
                size--;
            }

            // make next point to the right element of the level
            for (int i = 1; i < level.size(); i++) {
                Node n = level.get(i - 1);
                n.setNext(level.get(i));
            }

            q.addAll(level);
        }

        return root;
    }
}
