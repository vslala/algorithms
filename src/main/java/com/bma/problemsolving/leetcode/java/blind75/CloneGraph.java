package com.bma.problemsolving.leetcode.java.blind75;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class CloneGraph {
    public Node cloneGraph(Node node) {
        if (node == null) return node;
        var visited = new HashMap<Node, Node>();
        var q = new LinkedList<Node>();

        visited.put(node, new Node(node.val, new ArrayList<>()));
        q.offer(node);

        while (!q.isEmpty()) {
            Node curr = q.poll();

            for (Node neighbour : curr.neighbors) {
                if (!visited.containsKey(neighbour)) {
                    visited.put(neighbour, new Node(neighbour.val, new ArrayList<>()));
                    q.offer(neighbour);
                }

                visited.get(curr).neighbors.add(visited.get(neighbour));
            }
        }

        return visited.get(node);

    }


    @NoArgsConstructor
    @AllArgsConstructor
    public static class Node {
        int val;
        List<Node> neighbors;

        public Node(int val) {
            this.val = val;
        }

        public void addNeighbour(Node node) {
            this.neighbors.add(node);
            node.neighbors.add(this);
        }
    }
}
