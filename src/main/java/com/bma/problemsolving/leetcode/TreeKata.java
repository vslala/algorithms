package com.bma.problemsolving.leetcode;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;

import static java.lang.System.out;
import static java.util.Objects.isNull;

/**
 * 1. Ask for total nodes in the tree
 * 2. Generate random node value and insert in the tree
 * 3. Ask for commands to traverse the tree (like left, right, left, left, right)  from the root node
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
class Node {
    Node left;
    int val;
    Node right;
}

public class TreeKata {

    private Node root;
    private List<String> commands;
    private int nodeCount;
    private Random rand = new Random();

    public TreeKata(int nodeCount, String commands) {
        this.nodeCount = nodeCount;
        this.commands = Arrays.asList(commands.split(","));
    }


    public void print() {
        IntStream.range(0, nodeCount).forEach(node -> {
            final int val = rand.nextInt(90);
            out.print(val + ",");
            addNode(val);
        });
        out.println();

        inorder(root);

        Node curr = root;
        out.println("\nstarting with the root node - " + root.val);
        for (String command : commands) {
            if (command.equalsIgnoreCase("left")) {
                if (curr.left == null) {
                    out.println("Oops... no more nodes on the left");
                } else {
                    curr = curr.left;
                    out.println(curr.val);
                }
            } else {
                if (curr.right == null) {
                    out.println("Oops... no more nodes on the right");
                } else {
                    curr = curr.right;
                    out.println(curr.val);
                }
            }
        }


    }

    private void inorder(Node root) {
        if (root != null) {
            inorder(root.left);
            out.print(root.val + ", ");
            inorder(root.right);
        }
    }

    private void addNode(int val) {
        if (isNull(root)) {
            root = new Node(null, val, null);
            return;
        }

        insert(root, val);
    }

    private void insert(Node node, int val) {
        if (node.val < val) {
            if (node.right == null) {
                node.right = new Node(null, val, null);
                return;
            }
            insert(node.right, val);
        } else {
            if (node.left == null) {
                node.left = new Node(null, val, null);
                return;
            }
            insert(node.left, val);
        }
    }

}
