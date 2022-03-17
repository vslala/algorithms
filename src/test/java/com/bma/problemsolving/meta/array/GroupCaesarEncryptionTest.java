package com.bma.problemsolving.meta.array;

import com.bma.algorithms.sort.elementary.Util;
import com.bma.fixtures.Fixtures;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

class GroupCaesarEncryptionTest {

    private GroupCaesarEncryption sol = new GroupCaesarEncryption();

    @ParameterizedTest
    @CsvSource({
            "abc_bcd_acd_dfg, abc_bcd:acd_dfg",
            "abc_bcd_acd_dfg_ace_bdf_random, abc_bcd:acd_dfg:random:ace_bdf",
            "abc_bcd_acef_xyz_az_ba_a_z, acef:a_z:abc_bcd_xyz:az_ba",
            "abc_am, abc:am"
    })
    void shouldGroupCipherStringsTogether(String inputExpr, String expectedOutputExpr) {
        List<String> inputList = Fixtures.convertStringArrayToList(inputExpr.split("_"));
        String[] parts = expectedOutputExpr.split(":");
        List<List<String>> expected = new ArrayList<>();
        for (String part : parts) {
            expected.add(new ArrayList<>(List.of(part.split("_"))));
        }

        List<List<String>> output = sol.group(inputList);

        Util.println(expected);
        Util.println(output);
        assertTrue(expected.containsAll(output));
    }
}