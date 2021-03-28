package com.bma.algorithms.tries;

public class TrieNode {
    private static final int R = 256;   // extended ascii

    Object value;
    TrieNode[] next = new TrieNode[R];
}
