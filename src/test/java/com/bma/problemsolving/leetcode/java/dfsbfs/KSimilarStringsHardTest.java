package com.bma.problemsolving.leetcode.java.dfsbfs;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

class KSimilarStringsHardTest {

    private KSimilarStringsHard sol = new KSimilarStringsHard();

    @BeforeEach
    void setUp() {
    }

    @ParameterizedTest
    @CsvSource({
            "ab, ba, 1",
            "abc, bca, 2",
            "abac, baca, 2"
    })
    void shouldReturnTheSmallestNumberOfSwapsRequiredToTransformStringOneToStringTwo(String s1, String s2, int k) {
        int output = sol.kSimilarity(s1, s2);

        assertEquals(k, output);
    }
}