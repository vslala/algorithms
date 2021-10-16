package com.bma.problemsolving.leetcode.java.shortestpath;

import java.util.*;

/**
 * 743. Network Delay Time
 * ------------------------
 * You are given a network of n nodes, labeled from 1 to n.
 * You are also given times, a list of travel times as directed edges times[i] = (ui, vi, wi),
 * where ui is the source node,
 * vi is the target node,
 * and wi is the time it takes for a signal to travel from source to target.
 * <p>
 * We will send a signal from a given node k.
 * Return the time it takes for all the n nodes to receive the signal.
 * If it is impossible for all the n nodes to receive the signal, return -1.
 *
 * @author varun.shrivastava
 */
class NetworkDelayTime {
    public int networkDelayTime(int[][] times, int n, int k) {
        int result = -1;
        // construct a adjacency list matrix graph
        HashMap<Integer, List<Edge>> graph = constructAdjacencyListMatrixGraph(times);

        // need a priority queue to hold the closest node from a given node
        var pq = new PriorityQueue<Edge>(Comparator.comparingInt(e -> e.weight));
        // add the starting node to the queue
        pq.offer(new Edge(k, k, 0));

        // need to keep track of all the visited vertices
        var visited = new HashSet<Integer>();

        var processed = 0;
        while (!pq.isEmpty()) {
            var shortestEdge = pq.remove();

            if (visited.contains(shortestEdge.to)) {
                continue;
            }
            visited.add(shortestEdge.to);

            processed++;

            // 'Time' is updated, each time the signal 'hits' a node. We update the weight of a node (i.e. least time taken to hit it) below.
            // in the for-loop which processes the node's children.
            // Because we update the weight of a node on each iteration, and pq picks the currently CLOSEST node,
            // we guarantee that 'time's value, is minimized.
            result = shortestEdge.weight;
            if (processed == n) {
                return result;
            }

            // If the current node (call it Node A) has adjacent nodes, we want to update the distance to those adjacent nodes,
            // so that the priority-queue picks the NEXT closest node, knowing that we've come to Node A.
            if (graph.containsKey(shortestEdge.to)) {
                for (Edge edge : graph.get(shortestEdge.to)) {
                    pq.offer(new Edge(edge.from, edge.to, edge.weight + shortestEdge.weight));
                }
            }
        }

        return processed != n ? -1 : result;
    }

    private HashMap<Integer, List<Edge>> constructAdjacencyListMatrixGraph(int[][] times) {
        var graph = new HashMap<Integer, List<Edge>>();
        for (int[] time : times) {
            var from = time[0];
            var to = time[1];
            var networkDelay = time[2];
            graph.computeIfAbsent(from, v -> new ArrayList<>())
                    .add(new Edge(from, to, networkDelay));
        }
        return graph;
    }

    private static class Edge {
        int from;
        int to;
        int weight;

        public Edge(int from, int to, int weight) {
            this.from = from;
            this.to = to;
            this.weight = weight;
        }
    }
}
