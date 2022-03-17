package com.bma.problemsolving.meta.design;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class OrderedSetTest {

    private final OrderedSet<String> orderedSet = new OrderedSet<>();

    @BeforeEach
    void setup() {
        orderedSet.add("Varun");
        orderedSet.add("Vaibhav");
        orderedSet.add("John");
        orderedSet.add("Doe");
    }

    @Test
    void shouldAddNewItem() {
        assertEquals(4, orderedSet.size());
    }

    @Test
    void shouldReturnTrueIfTheValueExistsFalseOtherwise() {
        assertTrue(orderedSet.contains("Varun"));
        assertFalse(orderedSet.contains("Not Found"));
    }

    @Test
    void shouldReturnAllItemsInTheSameOrderTheyWereAdded() {
        orderedSet.add("Varun");
        List<String> items = orderedSet.toArray();

        int index = -1;
        assertEquals("Vaibhav", items.get(++index));
        assertEquals("John", items.get(++index));
        assertEquals("Doe", items.get(++index));
        assertEquals("Varun", items.get(++index));
    }

    @Test
    void shouldRemoveTheItemFromTheSet() {
        orderedSet.remove("Doe");

        assertEquals(3, orderedSet.size());
        List<String> items = orderedSet.toArray();

        int index = -1;
        assertEquals("Varun", items.get(++index));
        assertEquals("Vaibhav", items.get(++index));
        assertEquals("John", items.get(++index));
    }
}