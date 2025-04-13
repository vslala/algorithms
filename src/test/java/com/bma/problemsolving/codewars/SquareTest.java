package com.bma.problemsolving.codewars;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class SquareTest {
    @Test
    public void shouldWorkForSomeExamples() throws Exception {
        assertEquals("negative numbers aren't square numbers", Square.isSquare(-1));
        assertEquals("0 is a square number (0 * 0)",       Square.isSquare(0));
        assertEquals("3 isn't a square number",   Square.isSquare(3));
        assertEquals("4 is a square number (2 * 2)",       Square.isSquare(4));
        assertEquals("25 is a square number (5 * 5)",      Square.isSquare(25));
        assertEquals("26 isn't a square number",  Square.isSquare(26));
    }
}