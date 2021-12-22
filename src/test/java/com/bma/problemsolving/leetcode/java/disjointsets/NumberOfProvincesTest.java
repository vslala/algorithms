package com.bma.problemsolving.leetcode.java.disjointsets;

import com.bma.fixtures.Fixtures;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

class NumberOfProvincesTest {

    private NumberOfProvinces nop = new NumberOfProvinces();

    @ParameterizedTest
    @CsvSource({
            "[[1_0_0]:[0_1_0]:[0_0_1]], 3",
            "[[1_1_0]:[1_1_0]:[0_0_1]], 2"
    })
    void shouldReturnTheNumberOfProvincesThatAreNotConnectedToEachOther(String matrixStr, int expected) {
        int[][] isConnected = Fixtures.convertToPrimitiveArrMatrix(Fixtures.parseExpression(matrixStr));

        var result = nop.findCircleNum(isConnected);

        assertEquals(expected, result);
    }

}