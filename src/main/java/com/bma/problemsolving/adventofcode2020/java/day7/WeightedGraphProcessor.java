package com.bma.problemsolving.adventofcode2020.java.day7;

import lombok.RequiredArgsConstructor;

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;

@RequiredArgsConstructor
public class WeightedGraphProcessor {

    private final WeightedGraph graph;
    private final AtomicBoolean isBagFound = new AtomicBoolean(false);
    private final Set<String> part1 =  new HashSet<>();

    public int findAllBagsContainingBag(String bagColor) {
        Set<String> vertices = graph.get().keySet();
        vertices.forEach(vertex -> {
            isBagFound.set(false);
            dfs(vertex, graph, bagColor);
        });

        return part1.size();
    }

    private void dfs(String vertex, WeightedGraph graph, String bagColor) {
        if  (isBagFound.get() || vertex.equals(bagColor)) {
            isBagFound.set(true);
            return;
        }

        if (vertex.isEmpty()) return;

        graph.adj(vertex).forEach(edge -> {
            dfs(edge.other(vertex), graph, bagColor);
            if (isBagFound.get())
                part1.add(vertex);
        });
    }

    public int countAllBagsInsideABag(String bagColor) {
        return countTotalBags(bagColor);
    }

    private int countTotalBags(String bagColor) {
        if (graph.adj(bagColor).isEmpty())
            return 0;
        else
            return graph.adj(bagColor)
                    .stream()
                    .mapToInt(edge -> (int) (edge.getW() * (1 + countTotalBags(edge.other(bagColor)))))
                    .sum();
    }

}
