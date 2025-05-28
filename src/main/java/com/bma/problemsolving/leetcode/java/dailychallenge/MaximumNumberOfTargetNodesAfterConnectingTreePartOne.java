package com.bma.problemsolving.leetcode.java.dailychallenge;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Varun Shrivastava
 * @github www.github.com/vslala
 * @date 28/05/2025
 */
class MaximumNumberOfTargetNodesAfterConnectingTreePartOne {
    public int[] maxTargetNodes(int[][] edges1, int[][] edges2, int k) {
        int[] tree1EdgeCount = buildEdgeCount(edges1, k);
        int[] tree2EdgeCount = buildEdgeCount(edges2, k - 1);

        int max = 0;
        for (int v: tree2EdgeCount) {
            max = Math.max(v, max);
        }

        for (int i = 0; i < tree1EdgeCount.length; i++) {
            tree1EdgeCount[i] = tree1EdgeCount[i] + max;
        }

        return tree1EdgeCount;
    }

    private int[] buildEdgeCount(int[][] edges1, int k) {
        var children = new ArrayList<List<Integer>>(edges1.length + 1);
        for (int i = 0; i < edges1.length + 1; i++) {
            children.add(new ArrayList<>());
        }
        for(int[] edge: edges1) {
            int u = edge[0];
            int v = edge[1];
            children.get(u).add(v);
            children.get(v).add(u);
        }

        var edgeCount = new int[edges1.length + 1];
        for (int i = 0; i < children.size(); i++) {
            edgeCount[i] = dfs(i, -1, children, k);
        }
        return edgeCount;
    }

    private int dfs(int node, int parent, ArrayList<List<Integer>> children, int k) {
        if (k < 0) {
            return 0;
        }

        int count = 1;
        for (int child: children.get(node)) {
            if (child == parent) continue;
            count += dfs(child, node, children, k - 1);
        }

        return count;
    }
}
