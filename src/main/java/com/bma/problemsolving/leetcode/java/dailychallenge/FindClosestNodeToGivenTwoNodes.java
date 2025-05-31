package com.bma.problemsolving.leetcode.java.dailychallenge;

import java.util.*;

/**
 * Solves the LeetCode problem "Find Closest Node to Given Two Nodes".
 *
 * <p>Given a directed graph with each node having at most one outgoing edge, and two starting nodes,
 * finds the node that is reachable from both start nodes and minimizes the maximum distance from either.
 * If multiple such nodes exist, returns the node with the smallest index.</p>
 *
 * <p><b>Intuition:</b> For both start nodes, compute the shortest distance to every reachable node (using DFS).
 * For each node that is reachable from both, calculate the maximum of these two distances.
 * Return the node for which this value is minimized. In case of a tie, pick the node with the smallest index.</p>
 *
 * @author Varun Shrivastava
 * @github www.github.com/vslala
 * @date 30/05/2025
 */
class FindClosestNodeToGivenTwoNodes {

    /**
     * Simple record to store a node and the distance from the starting node.
     */
    private record NodeDistance(int node, int distance) {}

    /**
     * Finds the closest meeting node to two given nodes in the directed graph.
     *
     * @param edges The graph as an array; edges[i] = j means an edge from i to j, or -1 if no outgoing edge from i.
     * @param node1 The first starting node.
     * @param node2 The second starting node.
     * @return The node that is reachable from both node1 and node2 with minimal maximal distance, or -1 if no such node.
     *
     * <p>
     * 1. Builds an adjacency list from the edges.
     * 2. Computes the shortest distance from node1 to all reachable nodes.
     * 3. Computes the shortest distance from node2 to all reachable nodes.
     * 4. For every node reachable from both, finds the maximum of the two distances,
     *    and returns the node with the minimal such value (with smallest index in case of ties).
     * </p>
     */
    public int closestMeetingNode(int[] edges, int node1, int node2) {
        var graph = this.buildAdjListGraph(edges);

        // Compute distances from node1 to all reachable nodes.
        var nodeDistance = new TreeMap<Integer, Integer>();
        var visited = new HashSet<Integer>();
        dfs(graph, new NodeDistance(node1, 0), nodeDistance, visited);
        var nodeDistance1 = new TreeMap<>(nodeDistance);

        // Compute distances from node2 to all reachable nodes.
        nodeDistance.clear();
        visited.clear();
        dfs(graph, new NodeDistance(node2, 0), nodeDistance, visited);
        var nodeDistance2 = new TreeMap<>(nodeDistance);

        // Find the node with minimal maximal distance.
        int min = Integer.MAX_VALUE;
        int res = -1;
        for (int node: nodeDistance1.keySet()) {
            if (nodeDistance2.containsKey(node)) {
                int max = Math.max(nodeDistance1.get(node), nodeDistance2.get(node));
                if (max < min || (max == min && node < res)) {
                    min = max;
                    res = node;
                }
            }
        }
        return res;
    }

    /**
     * Builds an adjacency list representation of the graph.
     *
     * @param edges The array representation of the graph.
     * @return A HashMap mapping each node to its list of neighbors.
     *
     * <p>
     * Ensures that every node (even those with no outgoing edge) appears as a key in the map,
     * with an empty neighbor list if it has no outgoing edge.
     * </p>
     */
    private HashMap<Integer, List<Integer>> buildAdjListGraph(int[] edges) {
        var graph = new HashMap<Integer, List<Integer>>();
        for (int i = 0; i < edges.length; i++) {
            graph.putIfAbsent(i, new ArrayList<>());
            if (edges[i] != -1) {
                graph.get(i).add(edges[i]);
                graph.putIfAbsent(edges[i], new ArrayList<>()); // Defensive: ensure all nodes are present
            }
        }
        return graph;
    }

    /**
     * Performs a DFS traversal to compute distances from the starting node to all reachable nodes.
     *
     * @param graph        The adjacency list of the graph.
     * @param nd           A NodeDistance record holding the current node and the distance so far.
     * @param nodeDistance A map to record the shortest distance to each node from the start.
     * @param visited      A set to track visited nodes and avoid cycles.
     *
     * <p>
     * For every node visited, stores the shortest distance from the starting node.
     * Skips already visited nodes to prevent cycles and redundant work.
     * </p>
     */
    private void dfs(HashMap<Integer, List<Integer>> graph, NodeDistance nd,
                     TreeMap<Integer, Integer> nodeDistance, HashSet<Integer> visited) {
        if (visited.contains(nd.node)) {
            return;
        }

        nodeDistance.put(nd.node, nd.distance);
        visited.add(nd.node);
        for (int neigh : graph.get(nd.node)) {
            dfs(graph, new NodeDistance(neigh, nd.distance + 1), nodeDistance, visited);
        }
    }
}
