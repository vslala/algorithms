package com.bma.algorithms.tries.ternary;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class TrieTSTTest {

    @Test
    void itShouldPutKeyValPairInTheTrie() {
        var trie = new TrieTST<String>();
        trie.put("first_name", "Varun");
        assertTrue(trie.contains("first_name"));
    }

    @Test
    void itShouldGetTheValueByKey() {
        var trie = new TrieTST<String>();
        trie.put("first_name", "Varun");
        assertEquals("Varun", trie.get("first_name"));
    }
}