package com.bma.problemsolving.leetcode.java.dfsbfs;

import java.util.*;
import java.util.stream.IntStream;

/**
 * Find If Path Exists In Graph
 * There is a bi-directional graph with n vertices, where each vertex is labeled from 0 to n - 1 (inclusive). The edges in the graph are represented as a 2D integer array edges, where each edges[i] = [ui, vi] denotes a bi-directional edge between vertex ui and vertex vi. Every vertex pair is connected by at most one edge, and no vertex has an edge to itself.
 * You want to determine if there is a valid path that exists from vertex start to vertex end.
 * Given edges and the integers n, start, and end, return true if there is a valid path from start to end, or false otherwise.
 *
 * @author varun.shrivastava
 */
class FindIfPathExistsInGraph {

    public boolean validPath(int vertices, int[][] edges, int start, int end) {
        var dfsResult = new DFS(vertices, edges).validPath(start, end);
        var bfsResult = new BFS(vertices, edges).validPath(start, end);
        assert dfsResult == bfsResult;

        return dfsResult;
    }

    interface ValidPath {
        boolean validPath(int start, int end);

        default Map<Integer, List<Integer>> buildDiGraph(int vertices, int[][] edges) {
            Map<Integer, List<Integer>> graph = new HashMap<>();

            IntStream.range(0, vertices)
                    .forEach(v -> graph.put(v, new LinkedList<>()));

            IntStream.range(0, edges.length).forEach(v -> {
                graph.get(edges[v][0]).add(edges[v][1]);
                graph.get(edges[v][1]).add(edges[v][0]);
            });

            return graph;
        }
    }

    private static class DFS implements ValidPath {
        private Map<Integer, List<Integer>> graph;
        private boolean[] visited;
        private int vertices;

        public DFS(int vertices, int[][] edges) {
            this.vertices = vertices;
            this.graph = buildDiGraph(vertices, edges);
            this.visited = new boolean[vertices];
        }

        @Override
        public boolean validPath(int start, int end) {
            if (this.vertices == 1) return true;

            ArrayDeque<Integer> stack = new ArrayDeque<>();
            stack.push(start);
            while (!stack.isEmpty()) {
                int v = stack.pop();
                if (!visited[v]) {
                    visited[v] = true;
                    for (int u : graph.get(v)) {
                        if (u == end) return true;
                        if (!visited[u])
                            stack.push(u);
                    }
                }
            }

            return false;
        }
    }

    private static class BFS implements ValidPath {
        private Map<Integer, List<Integer>> graph;
        private int vertices;
        private boolean[] visited;

        public BFS(int totalVertices, int[][] edges) {
            this.vertices = totalVertices;
            this.graph = buildDiGraph(totalVertices, edges);
            this.visited = new boolean[totalVertices];
        }

        @Override
        public boolean validPath(int start, int end) {
            if (this.vertices == 1) return true;
            Deque<Integer> queue = new ArrayDeque<>();
            queue.offer(start);
            while (!queue.isEmpty()) {
                int v = queue.poll();
                for (int u: graph.get(v)) {
                    if (!visited[u]) {
                        if (u == end)
                            return true;
                        queue.offer(u);
                    }
                }
                visited[v] = true;
            }

            return false;
        }
    }
}
