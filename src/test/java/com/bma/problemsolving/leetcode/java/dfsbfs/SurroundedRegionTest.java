package com.bma.problemsolving.leetcode.java.dfsbfs;

import com.bma.fixtures.Fixtures;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class SurroundedRegionTest {

    private SurroundedRegion sol = new SurroundedRegion();

    @ParameterizedTest
    @CsvSource({
            "[[X_X_X_X]:[X_O_O_X]:[X_X_O_X]:[X_O_X_X]], [[X_X_X_X]:[X_X_X_X]:[X_X_X_X]:[X_O_X_X]]",
            "[[X_O_X_O_X_O]:[O_X_O_X_O_X]:[X_O_X_O_X_O]:[O_X_O_X_O_X]], [[X_O_X_O_X_O]:[O_X_X_X_X_X]:[X_X_X_X_X_O]:[O_X_O_X_O_X]]",
            "[[X_O_X]:[O_X_O]:[X_O_X]], [[X_O_X]:[O_X_O]:[X_O_X]]"
    })
    void shouldCheckIfRegionIsSurroundedByXThenSurroundTheRegion(String inputStr, String expectedOutputStr) {
        char[][] original = Fixtures.convertToPrimitiveCharMatrix(Fixtures.parseExpression(inputStr));
        char[][] input = Fixtures.convertToPrimitiveCharMatrix(Fixtures.parseExpression(inputStr));
        char[][] expected = Fixtures.convertToPrimitiveCharMatrix(Fixtures.parseExpression(expectedOutputStr));

        sol.surroundRegion(input);

        Fixtures.assertBothCharMatrixContainsSameItems(original, expected, input);
    }
}