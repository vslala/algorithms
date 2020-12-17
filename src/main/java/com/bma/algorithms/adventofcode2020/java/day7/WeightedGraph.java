package com.bma.algorithms.adventofcode2020.java.day7;

import java.util.*;
import java.util.stream.Collectors;

public class WeightedGraph {

    Map<String, List<WeightedEdge>> edges;

    public WeightedGraph() {
        edges = new HashMap<>();
    }

    public void addEdge(WeightedEdge edge) {
        if (edges.containsKey(edge.getV1()))
            edges.get(edge.getV1()).add(edge);
        else {
            final List<WeightedEdge> bag = new ArrayList<>();
            bag.add(edge);
            edges.put(edge.getV1(), bag);
        }
    }

    public int totalEdges() {
        int count = 0;
        for (Map.Entry<String, List<WeightedEdge>> vertex: edges.entrySet()) {
            count += vertex.getValue().size();
        }
        return count;
    }

    public List<WeightedEdge> adj(String v) {
        return edges.getOrDefault(v, Collections.emptyList());
    }

    public int totalVertices() {
        return edges.size();
    }

    public void print() {
        this.edges.forEach((key, val) -> {
            System.out.println(key + " -> " + val.stream().map(WeightedEdge::getV2).collect(Collectors.joining(" ")));
        });
    }

    public Map<String, List<WeightedEdge>> get() {
        return Collections.unmodifiableMap(edges);
    }

}
