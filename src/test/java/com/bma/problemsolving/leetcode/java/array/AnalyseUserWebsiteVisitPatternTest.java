package com.bma.problemsolving.leetcode.java.array;

import com.bma.fixtures.Fixtures;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

class AnalyseUserWebsiteVisitPatternTest {

    private AnalyseUserWebsiteVisitPattern sol = new AnalyseUserWebsiteVisitPattern();

    @ParameterizedTest
    @CsvSource({
            "joe_joe_joe_james_james_james_james_mary_mary_mary," +
                    "1_2_3_4_5_6_7_8_9_10," +
                    "home_about_career_home_cart_maps_home_home_about_career, home_about_career"
    })
    void shouldReturnTheMostVisitedPatternLexicographicallySortedByWebsiteName(String users, String timestamp, String websitesStr, String expectedStr) {
        String[] usernames = users.split("_");
        int[] timestamps = Fixtures.splitAndParseArr(timestamp, "_");
        String[] websites = websitesStr.split("_");
        var expected = Arrays.asList(expectedStr.split("_"));

        var result = sol.mostVisitedPattern(usernames, timestamps, websites);

        assertEquals(expected, result);
    }

}