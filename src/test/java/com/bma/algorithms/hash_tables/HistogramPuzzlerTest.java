package com.bma.algorithms.hash_tables;

import org.junit.jupiter.api.Test;

import java.util.stream.Collectors;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.*;

public class HistogramPuzzlerTest {

    @Test
    public void it_should_analyze_all_the_words_and_identify_the_polygene_lubricants_words() {

    }

    @Test
    public void generatePowerOf31() {
        String powerOf31 = IntStream.iterate(1, x ->  x * 31).limit(65).mapToObj(String::valueOf).collect(Collectors.joining(","));
        System.out.println(powerOf31);
    }

}