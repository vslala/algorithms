package com.bma.problemsolving.leetcode.java.array;

import com.bma.fixtures.Fixtures;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.Arrays;
import java.util.List;

class ExclusiveTimeOfFunctionsTest {

    private ExclusiveTimeOfFunctions sol = new ExclusiveTimeOfFunctions();

    @ParameterizedTest
    @CsvSource({
            "2, 0:start:0_1:start:2_1:end:5_0:end:6, 3_4",
            "1, 0:start:0_0:start:2_0:end:5_0:start:6_0:end:6_0:end:7, 8",
            "2, 0:start:0_0:start:2_0:end:5_1:start:6_1:end:6_0:end:7, 7_1"
    })
    void shouldReturnTheExclusiveTimeOfEachFunctionInAnArrayWhereValueOfIthIndexRepresentTheExclusiveTimeForTheFunctionWithIdI(int n, String expr, String expectedExpr) {
        List<String> logs = Arrays.asList(expr.split("_"));
        int[] expected = Fixtures.splitAndParseArr(expectedExpr, "_");

        var output = sol.exclusiveTime(n, logs);

        Fixtures.assertArrayEquals(expected, expected, output);
    }

}