package com.bma.problemsolving.leetcode.java;

import com.bma.fixtures.Fixtures;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class SearchSuggestionSystemTest {
    
    private SearchSuggestionSystem sol;

    @BeforeEach
    void setUp() {
        sol = new SearchSuggestionSystem();
    }
    
    @ParameterizedTest
    @CsvSource({
            "mobile_mouse_moneypot_monitor_mousepad, mouse, mobile_moneypot_monitor:mobile_moneypot_monitor:mouse_mousepad:mouse_mousepad:mouse_mousepad"
    })
    void shouldGenerateSearchBasedOnThePrefix(String inputStr, String searchWord, String expectedStr) {
        var input = inputStr.split("_");
        var expectedOutput = new ArrayList<List<String>>();
        for (String sentence: expectedStr.split(":")) {
            var words = new ArrayList<>(Arrays.asList(sentence.split("_")));
            expectedOutput.add(words);
        }

        var result = sol.suggestedProducts(input, searchWord);

        Fixtures.assertBothListsContainsSameItems(expectedOutput, result);
        
    }
}