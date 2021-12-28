package com.bma.problemsolving.leetcode.java.disjointsets;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 261. Graph Valid Tree
 * You have a graph of n nodes labeled from 0 to n - 1. You are given an integer n and a list of edges where edges[i] = [ai, bi] indicates that there is an undirected IEdge between nodes ai and bi in the graph.
 * Return true if the edges of the given graph make up a valid tree, and false otherwise.
 *
 * Definition of a valid tree
 * A valid tree is an acyclic graph in which all the vertices are connected
 * Number Of Edges are always  V - 1
 *
 * @author varun.shrivastava
 */
class GraphValidTree {

    public boolean validTree(int n, int[][] edges) {
        Graph graph = new Graph(n, edges);
        QuickUnion quickUnion = new QuickUnion(n, edges);

        return n - graph.totalEdges() == 1 && quickUnion.countOfDistinctComponents() == 1;
    }

    private static class Graph {
        private Map<Integer, List<Integer>> g;
        public Graph(int vertices, int[][] edges) {
            g = new HashMap<>();
            for (int i = 0; i < vertices; i++) {
                g.put(i, new ArrayList<>());
            }

            for (int[] IEdge : edges) {
                g.get(IEdge[0]).add(IEdge[1]);
                g.get(IEdge[1]).add(IEdge[0]);
            }
        }

        public int totalEdges() {
            AtomicInteger totalEdges = new AtomicInteger(0);
            g.values().stream().forEach(connectedEdges -> totalEdges.set(totalEdges.get() + connectedEdges.size()));

            return totalEdges.get() / 2;
        }
    }

    private static class QuickUnion {
        private final int[] parent;
        private final int[] height;
        private int count;

        public QuickUnion(int vertices, int[][] edges) {
            count = vertices;
            parent = new int[vertices];
            height = new int[vertices];
            for (int i = 0; i < vertices; i++) {
                parent[i] = i;
                height[i] = 1;
            }

            for (int[] IEdge : edges) {
                this.union(IEdge[0], IEdge[1]);
            }
        }

        /**
         * Keeps track of the number of distinct components
         * @return
         */
        public int countOfDistinctComponents() {
            return count;
        }

        /**
         * Recursively updates all the child nodes of the tree to point to parent
         * thus improving the runtime of the algorithm from log(n) to ackerman(n) close to constant
         *
         * @param p
         * @return
         */
        private int root(int p) {
            int root = p;
            while (root != parent[root]) {
                root = parent[root];
            }

            while (p != root) {
                int newP = parent[p];
                parent[p] = root;
                p = newP;
            }

            return root;
        }

        /**
         * Joins two components together
         * @param p
         * @param q
         */
        public void union(int p, int q) {
            int rootP = root(p);
            int rootQ = root(q);

            if (rootP == rootQ) return;

            if (height[rootP] > height[rootQ]) {
                parent[rootQ] = rootP;
                height[rootP] += height[rootQ];
            } else {
                parent[rootP] = rootQ;
                height[rootQ] += height[rootP];
            }

            count--;
        }
    }
}
