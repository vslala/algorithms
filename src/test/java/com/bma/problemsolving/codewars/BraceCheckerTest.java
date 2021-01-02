package com.bma.problemsolving.codewars;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BraceCheckerTest {
    private BraceChecker checker = new BraceChecker();

    @Test
    public void testValid() {
        assertTrue(checker.isValid("(){}[]"));
        assertTrue(checker.isValid("([{}])"));
        assertEquals(true, checker.isValid("()"));
    }

    @Test
    public void testInvalid() {
        assertEquals(false, checker.isValid("[(])"));
        assertFalse(checker.isValid("(}"));
        assertFalse(checker.isValid("[(])"));
        assertFalse(checker.isValid("[({})](]"));
        assertFalse(checker.isValid("(((({{"));
    }
}