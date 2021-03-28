package com.bma.algorithms.tries;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TrieSTTest {

    @Test
    void itShouldPutStringLiteralsIntoATrie() {
        var trie = new TrieST<String>();
        trie.put("key", "value");
        assertTrue(trie.contains("key"));
    }

    @Test
    void itShouldRetrieveTheValueStoredAtTheSpecificKey() {
        var trie = new TrieST<String>();
        trie.put("key", "value");
        assertEquals("value", trie.get("key"));
    }

    @Test
    void itShouldDeleteTheNodeByKey() {
        var trie = new TrieST<String>();
        trie.put("key", "value");
        trie.delete("key");
        assertFalse(trie.contains("key"));
    }

}