package com.bma.algorithms.hash_tables;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LinearProbingHashSTTest {

    LinearProbingHashST<String, String> linearProbe = new LinearProbingHashST<>();

    @Test
    public void it_should_put_object_in_the_hash_table() {
        String s1 = "Varun";
        String s2 = "Shrivastava";
        linearProbe.put(s1, s2);
        assertEquals(1, linearProbe.size());
    }

    @Test
    public void it_should_get_the_value_from_the_hashset() {
        String s1 = "Varun";
        String s2 = "Shrivastava";
        linearProbe.put(s1, s2);

        assertEquals(s2, linearProbe.get(s1));
    }

}