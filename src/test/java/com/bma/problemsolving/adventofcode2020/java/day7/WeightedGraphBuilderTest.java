package com.bma.problemsolving.adventofcode2020.java.day7;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class WeightedGraphBuilderTest {

    private static final String PATH =  "src/test/resources/adventofcode2020/inputs/day7test.txt";

    private WeightedGraphBuilder wgb;

    @BeforeEach
    void setup()  {
        wgb = new WeightedGraphBuilder();
    }

    @Test
    void itShouldBuildWeightedGraphFromFile() {
        WeightedGraph  wg = wgb.buildFromFileInput(Path.of(PATH));
        assertNotNull(wg);

//        wg.print();
    }

}