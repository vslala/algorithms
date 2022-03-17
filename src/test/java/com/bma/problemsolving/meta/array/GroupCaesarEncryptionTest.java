package com.bma.problemsolving.meta.array;

import com.bma.fixtures.Fixtures;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GroupCaesarEncryptionTest {

    private GroupCaesarEncryption sol = new GroupCaesarEncryption();

    @ParameterizedTest
    @CsvSource({
            "abc_bcd_acd_dfg, [[abc_bcd]:[acd_dfg]]",
            "abc_bcd_acd_dfg_ace_bdf_random, [[abc_bcd]:[acd_dfg]:[random]:[ace_bdf]]"
    })
    void shouldGroupCipherStringsTogether(String inputExpr, String expectedOutputExpr) {
        List<String> inputList = Fixtures.convertStringArrayToList(inputExpr.split("_"));
        List<List<String>> expected = Fixtures.parseExpression(expectedOutputExpr);

        List<List<String>> output = sol.group(inputList);

        for (List<String> o : expected) {
            for (List<String> e : output) {
                if (e.contains(o)) {
                    e.sort(Collections.reverseOrder());
                    o.sort(Collections.reverseOrder());
                    assertEquals(e, o);
                }
            }
        }
    }
}