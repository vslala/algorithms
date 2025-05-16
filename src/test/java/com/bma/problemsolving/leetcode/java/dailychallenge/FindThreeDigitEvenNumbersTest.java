package com.bma.problemsolving.leetcode.java.dailychallenge;

import com.bma.fixtures.Fixtures;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class FindThreeDigitEvenNumbersTest {

    @ParameterizedTest
    @CsvSource(value = {
            "[2,1,3,0] [102,120,130,132,210,230,302,310,312,320]"
    }, delimiter = ' ')
    void given_an_array_of_digits_find_the_even_3_digit_numbers(String inputExpr, String expectedExpr) {
        int[] digits = Fixtures.parse1DArray(inputExpr);
        int[] expected = Fixtures.parse1DArray(expectedExpr);

        var sol = new FindThreeDigitEvenNumbers();
        int[] output = sol.findEvenNumbers(digits);

        Fixtures.assertArrayEquals(digits, expected, output);
    }
}