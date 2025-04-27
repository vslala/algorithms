package com.bma.problemsolving.leetcode.java.citadel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GraphValidTree {

    private static class Graph {
        private int totalVertices;
        private Map<Integer, List<Integer>> adjList;
        public Graph(int totalVertices, int[][] edges) {
            this.totalVertices = totalVertices;
            this.adjList = new HashMap<>();
            for (int[] edge: edges) {
                int v1 = edge[0];
                int v2 = edge[1];

                this.adjList.computeIfAbsent(v1, vertex -> new ArrayList<>()).add(v2);
                this.adjList.computeIfAbsent(v2, vertex -> new ArrayList<>()).add(v1);
            }
        }

        public boolean hasCycle() {
            boolean[] visited = new boolean[this.totalVertices];
            return detectCycle(0, -1, visited);
        }

        private boolean detectCycle(int current, int parent, boolean[] visited) {
            visited[current] = true;

            for (int neighbor: this.adjList.getOrDefault(current, List.of())) {
                if (!visited[neighbor]) {
                    if (detectCycle(neighbor, current, visited)) {
                        return true;
                    }
                } else if(neighbor != parent) {
                    return true; // cycle detected, because we reached the same node that is not a parent again
                }
            }

            return false;
        }

        public boolean isConnected() {
            boolean[] visited = new boolean[this.totalVertices];
            detectCycle(0, -1, visited);
            for (boolean v: visited) {
                if (!v) return false;
            }
            return true;
        }
    }

    public boolean validTree(int n, int[][] edges) {
        var graph = new Graph(n, edges);
        return !graph.hasCycle() && graph.isConnected();
    }

}
