package com.bma.problemsolving.leetcode.java.dfsbfs;

import java.util.*;
import java.util.stream.IntStream;

/**
 * 1059. All Paths from Source Lead to Destination
 * Given the edges of a directed graph where edges[i] = [ai, bi] indicates there is an edge between nodes ai and bi, and two nodes source and destination of this graph, determine whether or not all paths starting from source eventually, end at destination, that is:
 * <p>
 * At least one path exists from the source node to the destination node
 * If a path exists from the source node to a node with no outgoing edges, then that node is equal to destination.
 * The number of possible paths from source to destination is a finite number.
 * Return true if and only if all roads from source lead to destination.
 *
 * @author varun.shrivastava
 */
class AllPathsFromSourceLeadToDestination {
    /**
     * WHITE: Initially all vertices are marked white (this can be looked as a default color)
     * GRAY : If the vertex is being processed (or is in the current stack)
     * BLACK: vertices that has been processed (or visited once)
     */
    private enum Color {
        WHITE,
        GRAY,
        BLACK
    }
    private int destination;
    private Color[] state;
    private Map<Integer, List<Integer>> graph;

    public boolean leadsToDestination(int n, int[][] edges, int source, int destination) {
        this.destination = destination;
        this.state = new Color[n];
        this.graph = new HashMap<>();

        Arrays.fill(state, Color.WHITE);
        IntStream.range(0, n).forEach(vertex -> graph.put(vertex, new LinkedList<>()));
        IntStream.range(0, edges.length).forEach(index -> graph.get(edges[index][0]).add(edges[index][1]));

        return doesItLeadsToDestination(source);
    }

    private boolean doesItLeadsToDestination(int currVertex) {
        // if state is gray, then it creates a backward edge, therefore it is a loop
        if (state[currVertex] != Color.WHITE) {
            return state[currVertex] == Color.BLACK;
        }

        // if destination node is the leaf node then return true
        if (graph.get(currVertex).isEmpty()) {
            return currVertex == destination;
        }

        // processing this node, so marking it as GRAY
        state[currVertex] = Color.GRAY;

        for (int u : graph.get(currVertex)) {
            // if we got `false` anywhere down the road, we short-circuit and return false
            if (!doesItLeadsToDestination(u))
                return false;
        }
        // recursive processing done for the node, so marking it as BLACK
        state[currVertex] = Color.BLACK;

        // if no negative stage found, return true
        return true;
    }
}
