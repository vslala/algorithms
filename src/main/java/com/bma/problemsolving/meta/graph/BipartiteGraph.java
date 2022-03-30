package com.bma.problemsolving.meta.graph;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashMap;
import java.util.Map;

/**
 * 785. Is Graph Bipartite?
 * There is an undirected graph with n nodes, where each node is numbered between 0 and n - 1. You are given a 2D array graph, where graph[u] is an array of nodes that node u is adjacent to. More formally, for each v in graph[u], there is an undirected edge between node u and node v. The graph has the following properties:
 *
 * There are no self-edges (graph[u] does not contain u).
 * There are no parallel edges (graph[u] does not contain duplicate values).
 * If v is in graph[u], then u is in graph[v] (the graph is undirected).
 * The graph may not be connected, meaning there may be two nodes u and v such that there is no path between them.
 * A graph is bipartite if the nodes can be partitioned into two independent sets A and B such that every edge in the graph connects a node in set A and a node in set B.
 *
 * Return true if and only if it is bipartite.
 * Ref: https://mathworld.wolfram.com/BipartiteGraph.html
 *
 * @author varun.shrivastava
 */
class BipartiteGraph {

    enum Color {
        BLACK,
        RED;

        static Color other(Color color) {
            return color == BLACK ? RED : BLACK;
        }

    }

    public boolean isBipartite(int[][] graph) {
        Map<Integer, Color> groups = new HashMap<>();
        Deque<Integer> stack = new ArrayDeque<>();
        // iterate over every vertex
        for (int vertex = 0; vertex < graph.length; vertex++) {
            // if the vertex is not visited already then process it
            if (!groups.containsKey(vertex)) {
                // push node into stack and set starting color to black
                stack.push(vertex);
                groups.put(vertex, Color.BLACK);

                while (!stack.isEmpty()) {
                    int v = stack.pop();
                    // color all its neighbours to RED
                    for (int w: graph[v]) {
                        if (groups.containsKey(w)) {
                            // already visited this vertex before, it means already colored
                            // neighbour's color shouldn't be the same as v
                            // so verify the same here
                            if (groups.get(w) == groups.get(v)) return false;
                        } else {
                            stack.push(w);
                            groups.put(w, Color.other(groups.get(v)));
                        }
                    }
                }
            }
        }
        return true;
    }
}
