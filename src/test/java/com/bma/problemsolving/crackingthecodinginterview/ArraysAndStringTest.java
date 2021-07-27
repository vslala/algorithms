package com.bma.problemsolving.crackingthecodinginterview;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ArraysAndStringTest {

    private ArraysAndString test = new ArraysAndString();

    @ParameterizedTest
    @CsvSource({
            "abc,true",
            "abcabc,false",
            "abcdefghijklmnopqrstuvwxyz,true",
            "shrivastava,false",
            "abbcd,false"
    })
    void  itShouldDetermineIfAStringHasAllUniqueCharactersWithoutUsingAdditionalDataStructure(String input, boolean output) {
        assertEquals(output, test.determineIfStringHasAllUniqueCharacters(input));
    }

    @ParameterizedTest
    @CsvSource({
            "test,esta,false",
            "geeksforgeeks,forgeeksgeeks,true",
            "google,ooggle,true"
    })
    void givenTwoStringsDecideIfOneIsAPermutationOfTheOther(String first, String second, boolean output) {
        assertEquals(output, test.isStringOnePermutationOfTheOther(first, second));
    }

    @ParameterizedTest
    @CsvSource({
            "Mr John Smith,Mr%20John%20Smith",
            "Good Morning,Good%20Morning",
            "IAMAWESOME,IAMAWESOME"
    })
    void itShouldReplaceAllSpacesInAStringWithPercentage20(String input, String output) {
        assertEquals(output, test.urlify(input));
    }

    @ParameterizedTest
    @CsvSource({
            "Tact Coa,true",
            "Varun Shrivastava,false",
            "aa bb dd,true"
    })
    void itShouldCheckIfTheStringIsAPermutationOfAPalindrome(String input, boolean output) {
        assertEquals(output, test.palindromePermutation(input));
    }

    /**
     * One Away: There are three types of edits that can be performed on strings:
     * - insert a character
     * - remove a character
     * - replace a character
     */
    @ParameterizedTest
    @CsvSource({
            "pale, ple, true",
            "pales, pale, true",
            "pale, bale, true",
            "pale, bake, false",
            "xab, xa, true",
            "xabc, xa, false",
            "xabc, xabc, true",
            "xa, xab, true",
            "xa, xabc, false",
            "abc, abcdefgh, false"
    })
    void itShouldCheckIfTheyAreOneEditOrZeroEditAway(String firstWord, String secondWord, boolean expectedOutput) {
        assertEquals(expectedOutput, test.checkIfBothWordsAreOneOrZeroEditAway(firstWord, secondWord));
    }

    @ParameterizedTest
    @CsvSource({
            "aabcccccaaa, a2b1c5a3",
            "aaaaaaaaaaa, a11",
            "abcdefgh, abcdefgh",
            "aabbccddee, aabbccddee",
            "a,a",
            "abcddeccc, abcddeccc"
    })
    void itShouldCompressTheGivenStringByAppendingTheCharacterCountAfterEachCharacter(String text, String expectedOutput) {
        assertEquals(expectedOutput, test.compressText(text));
    }
}