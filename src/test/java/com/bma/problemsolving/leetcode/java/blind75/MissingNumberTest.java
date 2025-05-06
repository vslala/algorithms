package com.bma.problemsolving.leetcode.java.blind75;

import com.bma.fixtures.Fixtures;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MissingNumberTest {

    @ParameterizedTest
    @CsvSource(value = {
            "[[3,0,1]] 2",
            "[[0,1]] 2",
            "[[9,6,4,2,3,5,7,0,1]] 8"
    }, delimiter = ' ')
    void it_should_find_the_missing_natural_number(String inputExpr, int expected) {
        int[] nums = Fixtures.convertToPrimitiveArrMatrix(Fixtures.parseNestedArrExpression(inputExpr, Integer.class))[0];
        var sol = new MissingNumber();


        var output = sol.missingNumber(nums);

        assertEquals(expected, output);
    }

}