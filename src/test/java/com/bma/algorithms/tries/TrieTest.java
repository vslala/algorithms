package com.bma.algorithms.tries;

import com.bma.algorithms.tries.Trie;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TrieTest {

    @Test
    void  itShouldInsertCharIntoTheTrie() {
        var trie = new Trie();
        trie.insert("Varun");
        trie.insert("Vikram");
        trie.insert("Varsha");
        trie.insert("Arun");

        assertTrue(trie.query("Varun"));
        assertFalse(trie.query("Abu"));
    }

    @Test
    void itShouldGiveTheCommonPrefix() {
        var trie = new Trie();
        String prefix = trie.commonPrefix(new String[]{"ab", "a"});
        assertEquals("a", prefix);
    }

}