package com.bma.algorithms.tries;

public interface ProcessorST {
    Iterable<String> keys();

    Iterable<String> keysWithPrefix(String s);

    Iterable<String> keysThatMatch(String s);

    String longestPrefixOf(String s);
}
