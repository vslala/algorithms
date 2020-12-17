package com.bma.algorithms.adventofcode2020;

import com.bma.algorithms.adventofcode2020.java.Day2;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.*;

class Day2Test {

    private static final String inputFile = "/Users/in-varun.shrivastava/Code/src/github.com/java/Algorithms/src/main/java/com/bma/algorithms/adventofcode2020/inputs/day2.txt";

    @Test
    void itShouldTellIfTheGivenPasswordSatisfiesThePolicy() {
        var p = new Day2();
        p.check("1-3 a: abcde");
        assertTrue(p.isValid());
        assertTrue(p.isCorrect());
    }

    @Test
    void sandbox() throws IOException {
        var p = new Day2();
        long count = Files.newBufferedReader(Path.of(inputFile)).lines()
                .filter(line -> {
                    p.check(line);
                    return p.isCorrect();
                })
                .count();
        System.out.println(count);
    }


}