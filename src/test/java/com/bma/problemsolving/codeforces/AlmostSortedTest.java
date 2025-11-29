package com.bma.problemsolving.codeforces;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AlmostSortedTest {

    @Test
    void itShouldSwapOrReverseTheSubArrayToMakeItSorted() {
        var inputs = Map.<List<Integer>, String>of(
                List.of(1, 2, 3, 4), "yes",
                List.of(4, 2), "no",
                List.of(3, 1, 2), "no",
                List.of(1, 5, 4, 3, 2, 6), "no"
        );

        var almostSorted = new AlmostSorted();

        inputs.forEach((input, expectedOutput) -> {
            String result = almostSorted.check(input, expectedOutput);
            assertEquals(expectedOutput, result);
        });
    }

}