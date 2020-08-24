package com.bma.algorithms.hash_tables;

import com.bma.algorithms.sort.elementary.Util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Map;

import static java.util.Arrays.stream;
import static java.util.Collections.emptyList;
import static java.util.stream.Collectors.groupingBy;

/**
 * Polygene words are the words that produces a hascode value equals to Integer.MIN_VALUE.
 * "polygenelubricants".hashCode() => -2147483648
 */
public class HistogramPuzzler {

//    private static final String [] words = {"I", "recommend", "polygene", "lubricants"};

    private final int[] powersOf31 = new int[]{
            1, 31, 961, 29791, 923521, 28629151, 887503681, 1742810335, -1807454463, -196513505, -1796951359, 129082719, -293403007, -505558625, 1507551809, -510534177, 1353309697, -997072353, -844471871, -408824225, 211350913, -2038056289, 1244764481, -67006753, -2077209343, 31019807, 961614017, -254736545, 693101697, 11316127, 350799937, -2010103841, 2111290369, 1025491999, 1725480897, 1950300255, 329765761, 1632803999, -922683583, 1461579999, -1935660287, 124073247, -448696639, -1024693921, -1700740479, -1183347297, 1970939457, 969581023, -7759359, -240540129, 1133190593, 769170015, -1925533311, 438009503, 693392705, 20337375, 630458625, -1930619105, 280349889, 100911967, -1166696319, -1807847521, -208698303, 2120287199, 1304393729
    };

    public void shortlistPolygeneLubricants(String[] words) {
        System.out.printf("Total Words: %d \n", words.length);

        Map<Integer, Map<Integer, List<String>>> byLengthByHashCode = stream(words).collect(groupingBy(String::length, groupingBy(String::hashCode)));

        stream(words).sorted().parallel().forEach(left ->
                byLengthByHashCode.forEach((length, byHash) -> {
                    int targetHash = Integer.MIN_VALUE - left.hashCode() * powersOf31[length];
                    List<String> rights = byHash.getOrDefault(targetHash, emptyList());
                    rights.forEach(right -> System.out.println(left + right));
                }));
    }

    public static void main(String[] args) throws IOException {
        String[] words = Files.readAllLines(Paths.get("src/main/resources/words.txt")).toArray(new String[]{});
        Util.measureRunTime(() -> new HistogramPuzzler().shortlistPolygeneLubricants(words));
    }
}
