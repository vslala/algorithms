package com.bma.algorithms.adventofcode2020.java.day7;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertEquals;

class WeightedGraphProcessorTest {
    private static final String PATH =  "/Users/in-varun.shrivastava/Code/src/github.com/java/Algorithms/src/main/java/com/bma/algorithms/adventofcode2020/inputs/day7.txt";
    private WeightedGraph weightedGraph;
    private WeightedGraphProcessor graphProcessor;

    @BeforeEach
    void setup() {
        var graphBuilder = new WeightedGraphBuilder();
        weightedGraph = graphBuilder.buildFromFileInput(Path.of(PATH));
        graphProcessor = new WeightedGraphProcessor(weightedGraph);
    }

    @Test
    void itShouldFindAllTheBagsThatContainsShinyGoldBags() {
        int count = graphProcessor.findAllBagsContainingBag("shiny gold");
        assertEquals(238, count);
    }

    @Test
    void itShouldFindCountOfAllTheBagsWithInShinyGoldBagRecursively()  {
        int count = graphProcessor.countAllBagsInsideABag("shiny gold");
        assertEquals(82930, count);
    }
}
