package com.bma.algorithms.hash_tables;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ConcordanceIndexingTest {

    public static final String TALE_OF_TWO_CITIES_FILE_PATH = "src/main/resources/tale-of-two-cities.txt";

    @Test
    public void itShouldPrintGivenNumberWordsBeforeAndAfterTheGivenWords() {
        var concordance = new ConcordanceIndexing(TALE_OF_TWO_CITIES_FILE_PATH);
        String context = concordance.findContextForWord("the");
        System.out.println(context);
        assertNotNull(context);
        assertFalse(context.isEmpty());
    }

}