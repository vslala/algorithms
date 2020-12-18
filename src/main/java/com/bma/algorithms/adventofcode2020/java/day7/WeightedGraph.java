package com.bma.algorithms.adventofcode2020.java.day7;

import com.bma.algorithms.graphs.Bag;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class WeightedGraph {

    private int totalEdges = 0;
    private Map<String, Bag<WeightedEdge>> edges;

    public WeightedGraph() {
        edges = new HashMap<>();
    }

    public void addEdge(WeightedEdge edge) {
        if (edges.containsKey(edge.getV1()))
            edges.get(edge.getV1()).add(edge);
        else {
            final Bag<WeightedEdge> bag = new Bag<>();
            bag.add(edge);
            edges.put(edge.getV1(), bag);
        }
        totalEdges += 1;
    }

    public int totalEdges() {
        return totalEdges;
    }

    public List<WeightedEdge> adj(String v) {
        return edges.getOrDefault(v, Bag.empty());
    }

    public int totalVertices() {
        return edges.size();
    }

    public void print() {
        this.edges.forEach((key, val) ->
                System.out.println(key + " -> " + val.stream()
                        .map(WeightedEdge::getV2)
                        .collect(Collectors.joining(" "))));
    }

    public Map<String, List<WeightedEdge>> get() {
        return Collections.unmodifiableMap(edges);
    }

}
