package com.bma.problemsolving.adventofcode2020.java.day7;

import com.bma.algorithms.graphs.model.Bag;

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

    public void addEdge(WeightedEdge IEdge) {
        if (edges.containsKey(IEdge.getV1()))
            edges.get(IEdge.getV1()).add(IEdge);
        else {
            final Bag<WeightedEdge> bag = new Bag<>();
            bag.add(IEdge);
            edges.put(IEdge.getV1(), bag);
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
