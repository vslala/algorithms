package com.bma.problemsolving.leetcode.java.blind75;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author Varun Shrivastava
 * @github www.github.com/vslala
 * @date 16/05/2025
 */
class NumberOfConnectedComponents {
    public int countComponents(int n, int[][] edges) {
        var graph = new HashMap<Integer, List<Integer>>();
        for (int i = 0; i < n; i++) {
            graph.put(i, new ArrayList<>());
        }

        for (int[] edge: edges) {
            int u = edge[0];
            int v = edge[1];
            graph.get(u).add(v);
            graph.get(v).add(u);
        }

        int connectedComponent = 0;
        boolean[] visited = new boolean[n];
        for (int i = 0; i < n; i++) {
            if (visited[i]) continue;
            dfs(i, visited, graph);
            connectedComponent += 1;
        }

        return connectedComponent;
    }

    private void dfs(int v, boolean[] visited, HashMap<Integer, List<Integer>> graph) {
        visited[v] = true;

        for (int edge: graph.get(v)) {
            if (!visited[edge]) {
                dfs(edge, visited, graph);
            }
        }
    }
}
