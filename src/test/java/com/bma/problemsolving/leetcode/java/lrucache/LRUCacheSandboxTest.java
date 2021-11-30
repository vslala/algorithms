package com.bma.problemsolving.leetcode.java.lrucache;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class LRUCacheSandboxTest {

    @Test
    void itShouldMaintainALeastRecentlyUsedCachedAndPerformGetAndPutOperationInO1Time() {
        var lRUCache =  new LRUCache<Integer,Integer>(2);
        lRUCache.defaultMissValue(-1);

        lRUCache.put(1, 1); // cache is {1=1}
        lRUCache.put(2, 2); // cache is {1=1, 2=2}
        assertEquals(1, lRUCache.get(1));    // return 1

        lRUCache.put(3, 3); // LRU key was 2, evicts key 2, cache is {1=1, 3=3}
        assertEquals(-1, lRUCache.get(2));    // returns -1 (not found)

        lRUCache.put(4, 4); // LRU key was 1, evicts key 1, cache is {4=4, 3=3}
        assertEquals(-1, lRUCache.get(1));    // return -1 (not found)
        assertEquals(3, lRUCache.get(3));    // return 3
        assertEquals(4, lRUCache.get(4));    // return 4
    }


}