package com.bma.algorithms.adventofcode2020.java.day7;

import lombok.RequiredArgsConstructor;

import java.util.*;

@RequiredArgsConstructor
public class WeightedGraphProcessor {

    private Map<String, Boolean> visited;
    private final WeightedGraph graph;
    private boolean isBagFound = false;
    private Set<String> part1 =  new HashSet<>();


    public int findAllBagsContainingBag(String bagColor) {
        visited = new HashMap<>();
        Set<String> vertices = graph.get().keySet();
        for  (String vertex : vertices) {
            isBagFound = false;
            dfs(vertex, graph, bagColor);
        }

//        part1.forEach(v -> System.out.print(v + ", "));
//        System.out.println();
        return part1.size();
    }

    private void dfs(String vertex, WeightedGraph graph, String bagColor) {
        if  (isBagFound || vertex.equals(bagColor)) {
            isBagFound = true;
            return;
        }

        if (vertex.isEmpty())
            return;
        

        visited.put(vertex, true);
        for (WeightedEdge edge: graph.adj(vertex))  {
            dfs(edge.either(vertex), graph, bagColor);
            if (isBagFound)
                part1.add(vertex);
        }

    }


}
