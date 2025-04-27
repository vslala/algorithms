package com.bma.problemsolving.leetcode.java.citadel;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CountGoodNumbersTest {

    private CountGoodNumbers sol;

    @Test
    void it_should_return_count_of_good_numbers_for_one_digit_numbers() {
        sol = new CountGoodNumbers();
        int output = sol.countGoodNumbers(1);

        assertEquals(5, output);
    }

    @Test
    void it_should_return_count_of_good_numbers_for_2_digit_numbers() {
        sol = new CountGoodNumbers();
        int output = sol.countGoodNumbers(2);
        assertEquals(20, output);
    }

    @Test
    void it_should_return_count_of_good_numbers_for_50_digit_numbers() {
        sol = new CountGoodNumbers();
        int output = sol.countGoodNumbers(50);
        assertEquals(564908303, output);
    }

    @Test
    void it_should_return_count_of_good_numbers_for_1924_digit_numbers() {
        sol = new CountGoodNumbers();
        int output = sol.countGoodNumbers(1924);
        assertEquals(805821919, output);
    }
}