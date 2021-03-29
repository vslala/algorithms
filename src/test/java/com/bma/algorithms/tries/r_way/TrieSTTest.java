package com.bma.algorithms.tries.r_way;

import com.bma.algorithms.tries.r_way.TrieST;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

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

    @Test
    void sandbox() {

        String str = "I LIKE APPLES.";
        var trie = new TrieST<Integer>();
        Arrays.stream(str.split("\\s+"))
                .forEach(word -> trie.put(word, word.length()));

        assertTrue(trie.contains("LIKE"));
        /*
            I:73
             :32
            L:76
            O:79
            V:86
            E:69
             :32
            A:65
            P:80
            P:80
            L:76
            E:69
            S:83
            .:46
         */
        for (char c : str.toCharArray()) {
            System.out.print("<td>" + c + "</td>");
        }
        System.out.println();
        for (char c : str.toCharArray()) {
            System.out.print("<td>" + (int)c + "</td>");
        }
    }

}