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
        if (vertices == 1) return true;

        Map<Integer, List<Integer>> graph = new HashMap<>();
        IntStream.range(0, vertices).forEach(v -> graph.put(v, new LinkedList<>()));
        IntStream.range(0, edges.length).forEach(v -> {
            graph.get(edges[v][0]).add(edges[v][1]);
            graph.get(edges[v][1]).add(edges[v][0]);
        });

        boolean[] visited = new boolean[vertices];
        ArrayDeque<Integer> stack = new ArrayDeque<>();
        stack.push(start);
        while (!stack.isEmpty()) {
            int v = stack.pop();
            if (!visited[v]) {
                visited[v] = true;
                for(int u: graph.get(v)) {
                    if (u == end) return true;
                    if (!visited[u])
                        stack.push(u);
                }
            }
        }

        return false;
    }
}
