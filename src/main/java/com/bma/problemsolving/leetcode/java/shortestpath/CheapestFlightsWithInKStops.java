package com.bma.problemsolving.leetcode.java.shortestpath;

import com.bma.algorithms.graphs.directed_graphs.EdgeWeightedDigraph;
import com.bma.algorithms.graphs.model.DirectedEdge;

import java.util.Arrays;

/**
 * 787. Cheapest Flights Within K Stops
 * There are n cities connected by some number of flights. You are given an array flights where flights[i] = [fromi, toi, pricei] indicates that there is a flight from city fromi to city toi with cost pricei.
 *
 * You are also given three integers src, dst, and k, return the cheapest price from src to dst with at most k stops. If there is no such route, return -1.
 *
 * @author varun.shrivastava
 */
class CheapestFlightsWithInKStops {

    private double[] tempDistTo;
    private double[] distTo;

    public double findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
        EdgeWeightedDigraph digraph = EdgeWeightedDigraph.createDigraph(flights, n);
        tempDistTo = new double[n];
        distTo = new double[n];

        Arrays.fill(distTo, Double.POSITIVE_INFINITY);
        distTo[src] = 0;

        // at most k stops means k + 1 edges
        for (int i = 0; i < k + 1; i++) {
            tempDistTo = distTo.clone();
            for (DirectedEdge edge : digraph.edges()) {
                relax(edge);
            }
            distTo = tempDistTo;
        }

        return distTo[dst] == Double.POSITIVE_INFINITY ? -1 : distTo[dst];
    }

    private void relax(DirectedEdge edge) {
        int u = edge.from();
        int v = edge.to();
        double w = edge.weight();

        if (tempDistTo[v] > distTo[u] + w) {
            tempDistTo[v] = distTo[u] + w;
        }
    }
}
