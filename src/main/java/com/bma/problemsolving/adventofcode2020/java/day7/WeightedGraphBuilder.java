package com.bma.problemsolving.adventofcode2020.java.day7;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class WeightedGraphBuilder {
    /**
     * Sample Input
     * ----------------
     * light red bags contain 1 bright white bag, 2 muted yellow bags.
     * dark orange bags contain 3 bright white bags, 4 muted yellow bags.
     * bright white bags contain 1 shiny gold bag.
     * muted yellow bags contain 2 shiny gold bags, 9 faded blue bags.
     * shiny gold bags contain 1 dark olive bag, 2 vibrant plum bags.
     * dark olive bags contain 3 faded blue bags, 4 dotted black bags.
     * vibrant plum bags contain 5 faded blue bags, 6 dotted black bags.
     * faded blue bags contain no other bags.
     * dotted black bags contain no other bags.
     *
     * @param filePath
     * @return
     */
    private static final String CONTAIN_REGEX = " contain ";
    private static final int ZERO = 0;
    private static final String BAG_REGEX = "bag";
    private static final String COMMA_REGEX = ",";

    public WeightedGraph buildFromFileInput(Path filePath) {
        WeightedGraph wg = new WeightedGraph();
        try {
            Files.readAllLines(filePath).forEach(line ->
                    buildEdge(parseV1(line), line.split(CONTAIN_REGEX)[1].split(COMMA_REGEX))
                    .forEach(wg::addEdge));
        } catch (IOException e) {
            // never do this in production code
            e.printStackTrace();
        }

        return wg;
    }

    private String parseV1(String line) {
        // Line: light red bags contain 1 bright white bag, 2 muted yellow bags.
        // After Split:     light red bags
        // After Substring: light red
        return line.split(CONTAIN_REGEX)[ZERO]
                .substring(ZERO, line.split(CONTAIN_REGEX)[ZERO]
                        .indexOf(BAG_REGEX))
                .trim();
    }

    private List<WeightedEdge> buildEdge(String from, String[] edgesParts) {
        return Arrays.stream(edgesParts)
                .map(vw -> new WeightedEdge(from, vw.trim()))
                .collect(Collectors.toList());
    }


}
