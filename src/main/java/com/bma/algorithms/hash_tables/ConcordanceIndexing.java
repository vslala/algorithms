package com.bma.algorithms.hash_tables;

import lombok.RequiredArgsConstructor;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.stream.IntStream;

@RequiredArgsConstructor
public class ConcordanceIndexing {

    private final String inputFilePath;
    private String[] words;
    private final Map<String, Set<Integer>> symbolTable = new HashMap<>();

    public String findContextForWord(String word) {
        initSymbolTable();
        StringBuilder sb = new StringBuilder();
        if (! symbolTable.containsKey(word)) return "";
        symbolTable.get(word).forEach(wordIndex -> {
            IntStream.range(checkLowerBoundaryCondition(wordIndex), checkUpperBoundaryCondition(wordIndex))
                    .forEach(index -> sb.append(words[index]).append(" "));
            sb.append(System.lineSeparator());
        });
        return sb.toString();
    }

    private int checkUpperBoundaryCondition(Integer wordIndex) {
        return wordIndex + 4 >= words.length ? words.length - 1 : wordIndex + 4;
    }

    private int checkLowerBoundaryCondition(Integer wordIndex) {
        return wordIndex - 4 < 0 ? 0 : wordIndex - 4;
    }

    private void initSymbolTable() {
        try {
            words = Files.readString(Path.of(inputFilePath)).split("\\s+");
            IntStream.range(0, words.length).forEach(wordIndex -> {
                String word = words[wordIndex];
                if (!symbolTable.containsKey(word))
                    symbolTable.put(word, new HashSet<>());
                symbolTable.get(word).add(wordIndex);
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
