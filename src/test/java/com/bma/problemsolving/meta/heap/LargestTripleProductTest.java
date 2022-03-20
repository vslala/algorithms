package com.bma.problemsolving.meta.heap;

import com.bma.fixtures.Fixtures;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class LargestTripleProductTest {

    private LargestTripleProduct sol = new LargestTripleProduct();

    @ParameterizedTest
    @CsvSource(value = {
            "[[1,2,3,4,5]] [[-1,-1,6,24,60]]",
            "[[2,1,2,1,2]] [[-1,-1,4,4,8]]"
    }, delimiter = ' ')
    void shouldReturnTheArrayWithProductOfLargestTripleSeenSoFar(String inputExpr, String expectedExpr) {
        int[] arr = Fixtures.convertToPrimitiveArrMatrix(Fixtures.parseNestedArrExpression(inputExpr, Integer.class))[0];
        int[] expected = Fixtures.convertToPrimitiveArrMatrix(Fixtures.parseNestedArrExpression(expectedExpr, Integer.class))[0];

        int[] output = sol.findMaxProduct(arr);

        Fixtures.assertArrayEquals(arr, expected, output);
    }

}