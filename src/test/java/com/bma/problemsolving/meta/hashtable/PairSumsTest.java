package com.bma.problemsolving.meta.hashtable;

import com.bma.fixtures.Fixtures;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PairSumsTest {

    private PairSums sol = new PairSums();

    @ParameterizedTest
    @CsvSource(value = {
            "[[1,2,3,4,3]] 6 2",
            "[[1,5,3,3,3]] 6 4"
    }, delimiter = ' ')
    void shouldReturnTheCountOfPairThatSumsUpToTheGivenNumber(String inputExpr, int k, int expected) {
        int[] arr = Fixtures.convertToPrimitiveArrMatrix(Fixtures.parseNestedArrExpression(inputExpr, Integer.class))[0];

        int output = sol.numberOfWays(arr, k);

        assertEquals(expected, output);
    }
}