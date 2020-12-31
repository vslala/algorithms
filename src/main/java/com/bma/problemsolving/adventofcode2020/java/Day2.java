package com.bma.problemsolving.adventofcode2020.java;

import lombok.Getter;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

public class Day2 {
    @Getter
    private boolean isValid = false;

    @Getter
    private boolean isCorrect = false;

    public Day2() {
    }

    public void check(String line) {
        isCorrect = false;
        isValid = false;
        int hyphen = 0;
        int colon = 0;
        int character = 0;
        int wordStart = 0;
        for (int i=0; i < line.length(); i++) {
            if (line.charAt(i) ==  '-') hyphen = i;
            if (line.charAt(i) == ':') {
                colon = i;
                character = i - 1;
                wordStart = colon + 1;
            }
        }

        int firstPlace = Integer.parseInt(line.substring(0, hyphen));
        int secondPlace = Integer.parseInt(line.substring(hyphen + 1, character - 1));
        String word = line.substring(wordStart).trim();
        final char c = line.charAt(character);

//        System.out.println(String.format("MIN: %d, MAX: %d, CHAR: %c, WORD: %s", firstPlace, secondPlace, line.charAt(character), word));

        AtomicInteger count = new AtomicInteger();
        IntStream.range(0, word.length()).forEach(index -> {
            if (word.charAt(index) == c) count.incrementAndGet();
        });

        isCorrect = (word.charAt(firstPlace - 1) == c && word.charAt(secondPlace - 1) != c) ||
                (word.charAt(firstPlace - 1) != c && word.charAt(secondPlace - 1) == c);
        isValid = count.get() >= firstPlace && count.get() <= secondPlace;
    }
}
