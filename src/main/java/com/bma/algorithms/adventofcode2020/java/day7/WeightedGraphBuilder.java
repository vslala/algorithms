package com.bma.algorithms.adventofcode2020.java.day7;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
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
    public static final String CONTAIN_REGEX = " contain ";

    public WeightedGraph buildFromFileInput(Path filePath) {
        WeightedGraph wg = new WeightedGraph();
        try {
            Files.readAllLines(filePath).forEach(line -> {
                buildEdge(parseV1(line), line.split(CONTAIN_REGEX)[1].split(","))
                        .forEach(wg::addEdge);
            });
        } catch (IOException e) {
            e.printStackTrace();
        }

        return wg;
    }

    private String parseV1(String line) {

        return line.split(CONTAIN_REGEX)[0]
                .substring(0, line.split(CONTAIN_REGEX)[0]
                        .indexOf("bag"))
                .trim(); // light red bags
    }

    private List<WeightedEdge> buildEdge(String from, String[] edgesParts) {
        return Arrays.stream(edgesParts)
                .map(vw -> new WeightedEdge(from ,vw.trim()))
                .collect(Collectors.toList());
    }


}
