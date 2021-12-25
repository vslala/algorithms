package com.bma.problemsolving.leetcode.java.dfsbfs;

import java.util.ArrayList;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

/**
 * All Paths From Source To Target
 * <p>
 * Given a directed acyclic graph (DAG) of n nodes labeled from 0 to n - 1, find all possible paths from node 0 to node n - 1 and return them in any order.
 * The graph is given as follows: graph[i] is a list of all nodes you can visit from node i (i.e., there is a directed edge from node i to node graph[i][j]).
 *
 * @author varun.shrivastava
 */
class AllPathsFromSourceToTarget {
    private int[][] graph;
    private int lastVertex;

    public List<List<Integer>> allPathsSourceTarget(int[][] graph) {
        this.graph = graph;
        this.lastVertex = graph.length - 1;
        int startVertex = 0;

        List<List<Integer>> result = new ArrayList<>();
        Deque<Integer> path = new LinkedList<>();
        path.add(startVertex);

        dfs(startVertex, result, path);

        return result;
    }

    /**
     * Backtrack to visit all the edges and add it to the path
     * Once the end vertex is reached, then add the traversed path to the result
     *
     * @param curr
     * @param result
     * @param path
     */
    private void dfs(int curr, List<List<Integer>> result, Deque<Integer> path) {
        if (curr == lastVertex) {
            result.add(new LinkedList<>(path));
            return;
        }

        for (int u: graph[curr]) {
            path.addLast(u);
            dfs(u, result, path);
            path.removeLast();
        }
    }
}
