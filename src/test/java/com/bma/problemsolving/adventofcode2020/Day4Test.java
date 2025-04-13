package com.bma.problemsolving.adventofcode2020;

import com.bma.problemsolving.adventofcode2020.java.Day4;
import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class Day4Test {

    @Test
    void itShouldTellWhetherTheGivenPasswordIsValidOrNot() {
        var testData = Map.of(
                "ecl:gry pid:860033327 eyr:2020 hcl:#fffffd byr:1937 iyr:2017 cid:147 hgt:183cm", true,
                "iyr:2013 ecl:amb cid:350 eyr:2023 pid:028048884 hcl:#cfa07d byr:1929", false,
                "hcl:#ae17e1 iyr:2013 eyr:2024 ecl:brn pid:760753108 byr:1931 hgt:179cm", true,
                "hcl:#cfa07d eyr:2025 pid:166559648 iyr:2011 ecl:brn hgt:59in", false
        );
        var testClass = new Day4();

        testData.forEach((passportDetails, val) -> {
            if (val)
                assertTrue(testClass.isValid(passportDetails));
            else
                assertFalse(testClass.isValid(passportDetails));
        });

    }

}