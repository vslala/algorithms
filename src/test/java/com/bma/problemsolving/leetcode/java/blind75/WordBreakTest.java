package com.bma.problemsolving.leetcode.java.blind75;

import com.bma.problemsolving.leetcode.java.LeetCodeInputExpressionParser;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class WordBreakTest {
    @ParameterizedTest
    @CsvSource(value = {
            "leetcode [[leet,code]] true",
            "applepenapple [[apple,pen]] true",
            "catsandog [[cats,dog,sand,and,cat]] false",
            "varunandpriyankaarerocking [[are,varun,rocking,priyanka]] false",
            "anything [[]] false",
            "aaaaaaa [[a,aa,aaa]] true",
            "helloworld [[x,y,z]] false",
            "cars [[car,ca,rs]] true",
            "applepenapple [[apple,pen,applepen]] true",
            "aaaaaaaaaaaaaaaaaaaaaaab [[a,aa,aaa,aaaa,aaaaa]] false",
            "catsanddog [[cat,cats,and,sand,dog,random]] true",
            "bb [[a,b,bbb,bbbb]] true"
    }, delimiter = ' ')
    void it_should_return_whether_the_string_can_be_spaced_into_the_dict_words_provided(String input, String expr, boolean expected) {
        List<List<String>> dict = LeetCodeInputExpressionParser.parseNestedArrExpression(expr, ',', String.class);
        var sol = new WordBreak();
        boolean output = sol.wordBreakWithTrie(input, dict.getFirst());

        assertEquals(expected, output);
    }
}